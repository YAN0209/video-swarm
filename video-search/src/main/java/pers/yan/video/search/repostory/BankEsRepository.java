package pers.yan.video.search.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pers.yan.video.search.pojo.entity.BankEs;

/**
 * es 查询
 *
 * @author likaiyan
 * @date 2020/9/30 4:15 下午
 */
public interface BankEsRepository extends ElasticsearchRepository<BankEs, Long> {

    Page<BankEs> findByFirstnameOrLastnameOrAddressOrEmployer(String firstname, String lastname, String address, String employer, Pageable pageable);


}
