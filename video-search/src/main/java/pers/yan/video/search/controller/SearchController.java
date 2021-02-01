package pers.yan.video.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.yan.video.common.common.ResponseResult;
import pers.yan.video.search.dto.CommonPage;
import pers.yan.video.search.pojo.entity.VideoGroupEs;
import pers.yan.video.search.service.ElasticSearchService;

/**
 * 搜索功能
 * @author likaiyan
 * @date 2020/11/6 12:00 下午
 */
@RestController
public class SearchController {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/search")
    public ResponseResult<CommonPage<VideoGroupEs>> search(String keyword,
                                                           @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                           @RequestParam(required = false, defaultValue = "30") Integer pageSize){
        return ResponseResult.success(new CommonPage<>(elasticSearchService.get(keyword, pageNum, pageSize)));
    }

    @GetMapping("/importall")
    public ResponseResult<Integer> importAll(){
        return ResponseResult.success(elasticSearchService.importAll());
    }

}
