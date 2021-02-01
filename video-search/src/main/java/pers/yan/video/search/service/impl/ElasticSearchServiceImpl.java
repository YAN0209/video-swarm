package pers.yan.video.search.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.yan.video.search.dao.PersonMapper;
import pers.yan.video.search.dao.VideoGroupMapper;
import pers.yan.video.search.pojo.entity.*;
import pers.yan.video.search.repostory.VideoGroupEsRepository;
import pers.yan.video.search.service.ElasticSearchService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author likaiyan
 * @date 2020/10/12 11:17 上午
 */
@Service
public class ElasticSearchServiceImpl implements ElasticSearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchServiceImpl.class);

    @Autowired
    private VideoGroupEsRepository videoGroupEsRepository;

    @Autowired
    private VideoGroupMapper videoGroupMapper;

    @Autowired
    private PersonMapper personMapper;

    @Override
    public int importAll() {
        List<VideoGroup> videoGroups = videoGroupMapper.selectList(new QueryWrapper<>());
        if (!CollectionUtils.isEmpty(videoGroups)) {
            List<VideoGroupEs> videoGroupEsList = convertToVideoGroupEs(videoGroups);
            videoGroupEsList.forEach(videoGroupEs -> videoGroupEsRepository.save(videoGroupEs));
            return videoGroups.size();
        }
        return 0;
    }

    @Override
    public int importBatch(List<Integer> videoGroupIds) {
        List<VideoGroup> videoGroups = videoGroupMapper.selectBatchIds(videoGroupIds);
        if (!CollectionUtils.isEmpty(videoGroups)) {
            List<VideoGroupEs> videoGroupEsList = convertToVideoGroupEs(videoGroups);
            videoGroupEsList.forEach(videoGroupEs -> videoGroupEsRepository.save(videoGroupEs));
            return videoGroups.size();
        }
        return 0;
    }

    @Override
    public Page<VideoGroupEs> get(String keyword, int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.should(QueryBuilders.matchQuery("name", keyword))
                .should(QueryBuilders.matchQuery("imdbId", keyword))
                .should(QueryBuilders.matchQuery("alias", keyword))
                .should(QueryBuilders.matchQuery("code", keyword))
                .should(QueryBuilders.matchQuery("person", keyword))
                .should(QueryBuilders.matchQuery("videoTag", keyword))
                .should(QueryBuilders.matchQuery("videoType", keyword));
        LOGGER.info(boolQueryBuilder.toString());
        return videoGroupEsRepository.search(boolQueryBuilder, pageable);
    }

    @Override
    public void delete(long id) {
        videoGroupEsRepository.deleteById(id);
    }

    @Override
    public void save(VideoGroupEs entity) {
        videoGroupEsRepository.save(entity);
    }

    private List<VideoGroupEs> convertToVideoGroupEs(List<VideoGroup> videoGroups) {
        List<VideoGroupEs> videoGroupEss = new ArrayList<>(videoGroups.size());
        videoGroups.forEach(videoGroup -> {
            VideoGroupEs videoGroupEs = new VideoGroupEs();
            List<Person> persons = personMapper.getPersonByVideoGroupId(videoGroup.getId());
            List<VideoTag> tags = videoGroupMapper.getVideoTagByVideoGroupId(videoGroup.getId());
            List<VideoType> types = videoGroupMapper.getVideoTypeByVideoGroupId(videoGroup.getId());
            BeanUtils.copyProperties(videoGroup, videoGroupEs);
            videoGroupEs.setId(videoGroup.getId().longValue());
            if (!CollectionUtils.isEmpty(persons)) {
                videoGroupEs.setPerson(persons.stream().map(Person::getName).collect(Collectors.joining(",")));
            }
            if (!CollectionUtils.isEmpty(tags)) {
                videoGroupEs.setVideoTag(tags.stream().map(VideoTag::getName).collect(Collectors.joining(",")));
            }
            if (!CollectionUtils.isEmpty(types)) {
                videoGroupEs.setVideoType(types.stream().map(VideoType::getName).collect(Collectors.joining(",")));
            }
            videoGroupEss.add(videoGroupEs);
        });
        return videoGroupEss;
    }
}
