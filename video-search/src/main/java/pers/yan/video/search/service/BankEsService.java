package pers.yan.video.search.service;

import org.springframework.data.domain.Page;
import pers.yan.video.search.pojo.entity.BankEs;

/**
 * @author likaiyan
 * @date 2020/9/30 1:47 下午
 */
public interface BankEsService {

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<BankEs> search(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据关键字搜索名称或者副标题复合查询
     */
    Page<BankEs> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize,Integer sort);

    /**
     * 根据商品id推荐相关商品
     */
    Page<BankEs> recommend(Long id, Integer pageNum, Integer pageSize);

}