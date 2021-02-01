package pers.yan.video.search.pojo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * es 视频实体
 *
 * @author likaiyan
 * @date 2020/9/30 10:15 上午
 */
@Data
@Document(indexName = "videogroup", replicas = 0)
public class VideoGroupEs {

    @Id
    private Long id;

    private String name;

    private Float rate;

    @Field(type = FieldType.Keyword)
    private String imdbId;

    private String alias;

    private String poster;

    @Field(type = FieldType.Keyword)
    private String code;

    /**
     * personField
     * 所有相关人员,用','分割
     */
    private String person;

    /**
     * videoTagField
     * 所有视频标签,用','分割
     */
    private String videoTag;

    /**
     * videoTypeField
     * 所有类型标签,用','分割
     */
    private String videoType;

}
