package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * 分页传输类
 *
 * @author likaiyan
 * @date 2020/8/27 4:26 下午
 */
@Data
public class PageDto<T> {
    /**
     * 数据
     */
    protected List<T> records;
    /**
     * 总条数
     */
    protected long total;
    /**
     * 条数
     */
    protected long size;
    /**
     * 当前页
     */
    protected long current;
}
