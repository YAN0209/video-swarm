package pers.yan.video.search.repostory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import pers.yan.video.search.pojo.entity.VideoGroupEs;

/**
 * @author likaiyan
 * @date 2020/9/30 5:10 下午
 */
public interface VideoGroupEsRepository extends ElasticsearchRepository<VideoGroupEs, Long> {

    /**
     * 关键字搜索
     *
     * @param keyword  关键字
     * @param pageable 分页
     * @return 视频组分页实体
     */
    @Query("{" +
            "  \"query\": {" +
            "    \"bool\": {" +
            "      \"should\": [" +
            "        {" +
            "          \"match\": {" +
            "            \"name\": \"?0\"" +
            "          }" +
            "        }," +
            "        {" +
            "          \"match\": {" +
            "            \"imdbId\": \"?0\"" +
            "          }" +
            "        }," +
            "        {" +
            "          \"match\": {" +
            "            \"alias\": \"?0\"" +
            "          }" +
            "        }," +
            "        {" +
            "          \"match\": {" +
            "            \"code\": \"?0\"" +
            "          }" +
            "        }," +
            "        {" +
            "          \"match\": {" +
            "            \"person\": \"?0\"" +
            "          }" +
            "        }," +
            "        {" +
            "          \"match\": {" +
            "            \"videoTag\": \"?0\"" +
            "          }" +
            "        }," +
            "        {" +
            "          \"match\": {" +
            "            \"videoType\": \"?0\"" +
            "          }" +
            "        }" +
            "      ]" +
            "    }" +
            "  }" +
            "}")
    Page<VideoGroupEs> findByKeyword(String keyword, Pageable pageable);

}