package pers.yan.video.search.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pers.yan.video.search.pojo.entity.BankEs;
import pers.yan.video.search.repostory.BankEsRepository;
import pers.yan.video.search.service.BankEsService;

/**
 * @author likaiyan
 * @date 2020/9/30 1:51 下午
 */
@Service
public class BankEsServiceImpl implements BankEsService {

    @Autowired
    private BankEsRepository bankEsRepository;

    @Override
    public Page<BankEs> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return bankEsRepository.findByFirstnameOrLastnameOrAddressOrEmployer(keyword, keyword, keyword, keyword, pageable);
    }

    @Override
    public Page<BankEs> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
        throw new UnsupportedOperationException("Method not implemented.");
    }

    @Override
    public Page<BankEs> recommend(Long id, Integer pageNum, Integer pageSize) {
        throw new UnsupportedOperationException("Method not implemented.");
    }
}
