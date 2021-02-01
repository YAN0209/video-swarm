package pers.yan.video.search.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 通用分页
 *
 * @author likaiyan
 * @date 2020/11/6 11:47 上午
 */
@Data
public class CommonPage<T> {

    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    public CommonPage(Page<T> page){
        this.pageNum = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPage = page.getTotalPages();
        this.total = page.getTotalElements();
        this.list = page.getContent();
    }

}
