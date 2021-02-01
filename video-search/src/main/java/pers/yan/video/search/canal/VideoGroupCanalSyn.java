package pers.yan.video.search.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.alibaba.otter.canal.protocol.exception.CanalClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import pers.yan.video.search.dao.VideoGroupMapper;
import pers.yan.video.search.service.ElasticSearchService;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;

/**
 * 同步数据库到Es
 *
 * @author likaiyan
 * @date 2020/11/6 4:12 下午
 */
@Component
public class VideoGroupCanalSyn implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(VideoGroupCanalSyn.class);

    @Value("${canal.hostname}")
    private String hostname;

    @Value("${canal.destination}")
    private String destination;

    @Value("${canal.username}")
    private String username;

    @Value("${canal.password}")
    private String password;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @Autowired
    private VideoGroupMapper videoGroupMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        new Thread(this::runCanal).start();
    }

    private void runCanal() {
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(hostname, 11111),
                destination, username, password);

        boolean run = false;
        while (!run) {
            try {
                connector.connect();
                run = true;
                LOGGER.info("已连接canal");
            } catch (CanalClientException ex) {
                LOGGER.error("连接canal失败,3秒后重新连接", ex.getCause());
                try {
                    Thread.sleep(3000);
                } catch (Exception exception) {
                    //do nothing
                }
            }
        }

        while (true) {
            try {
                // 获取指定数量的数据
                Message message = connector.getWithoutAck(100);
                long batchId = message.getId();
                if (batchId != -1 && !CollectionUtils.isEmpty(message.getEntries())) {
                    synToElasticSearch(message.getEntries());
                }
                // 提交确认
                connector.ack(batchId);
            } catch (CanalClientException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    Thread.sleep(3000);
                } catch (Exception ex) {
                    // do nothing
                }
            }
        }
    }

    private void synToElasticSearch(List<CanalEntry.Entry> entries) {
        entries.forEach(entry -> {
            if (entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONBEGIN ||
                    entry.getEntryType() == CanalEntry.EntryType.TRANSACTIONEND) {
                return;
            }

            CanalEntry.RowChange rowChange = null;
            try {
                rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            CanalEntry.EventType eventType = rowChange.getEventType();
            LOGGER.info(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s",
                    entry.getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(),
                    entry.getHeader().getSchemaName(), entry.getHeader().getTableName(),
                    eventType));

            for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                if (eventType == CanalEntry.EventType.DELETE &&
                        "video_group".equals(entry.getHeader().getTableName())) {
                    elasticSearchService.delete(Long.parseLong(getValue("id", rowData.getBeforeColumnsList())));
                } else if (eventType == CanalEntry.EventType.DELETE) {
                    elasticSearchService.importBatch(getUpdateVideoGroupIds(entry.getHeader().getTableName(), rowData.getBeforeColumnsList()));
                } else if (eventType == CanalEntry.EventType.INSERT || eventType == CanalEntry.EventType.UPDATE) {
                    elasticSearchService.importBatch(getUpdateVideoGroupIds(entry.getHeader().getTableName(), rowData.getAfterColumnsList()));
                }
            }
        });
    }

    private List<Integer> getUpdateVideoGroupIds(String tableName, List<CanalEntry.Column> columns) {
        switch (tableName) {
            case "video_group":
                return Collections.singletonList(Integer.parseInt(getValue("id", columns)));
            case "person":
                return videoGroupMapper.getVideoGroupIdByPersonId(Integer.parseInt(getValue("id", columns)));
            case "writer":
            case "actor":
            case "director":
            case "video_group_type":
            case "video_group_tag":
                return Collections.singletonList(Integer.parseInt(getValue("group_id", columns)));
            case "video_tag":
                return videoGroupMapper.getVideoGroupIdByTagId(Integer.parseInt(getValue("id", columns)));
            case "video_type":
                return videoGroupMapper.getVideoGroupIdByTypeId(Integer.parseInt(getValue("id", columns)));
            default:
                return Collections.emptyList();
        }
    }

    private String getValue(String field, List<CanalEntry.Column> columns) {
        if (!CollectionUtils.isEmpty(columns)) {
            for (CanalEntry.Column column : columns) {
                if (field.equals(column.getName())) {
                    return column.getValue();
                }
            }
        }
        return "";
    }
}
