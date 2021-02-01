package pers.yan.video.search.service;

import org.springframework.data.domain.Page;
import pers.yan.video.search.pojo.entity.VideoGroupEs;

import java.util.List;

/**
 * es 服务封装
 *
 * @author likaiyan
 * @date 2020/9/29 5:37 下午
 */
public interface ElasticSearchService {

    /**
     * 导入所有数据
     *
     * @return 导入条数
     */
    int importAll();

    /**
     * 批量导入数据
     *
     * @param videoGroupIds 视频组id列表
     * @return 导入条数
     */
    int importBatch(List<Integer> videoGroupIds);

    /**
     * 查询数据
     *
     * @param keyword  关键字
     * @param pageNum  页码
     * @param pageSize 每页数据量
     * @return 数据列表
     */
    Page<VideoGroupEs> get(String keyword, int pageNum, int pageSize);

    /**
     * 删除数据
     *
     * @param id 主键
     */
    void delete(long id);

    /**
     * 保存数据
     *
     * @param entity 实体
     */
    void save(VideoGroupEs entity);

}