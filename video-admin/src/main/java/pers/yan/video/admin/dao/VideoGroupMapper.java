package pers.yan.video.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import pers.yan.video.admin.pojo.entity.VideoGroup;
import pers.yan.video.admin.pojo.entity.VideoGroupTag;
import pers.yan.video.admin.pojo.entity.VideoGroupType;

/**
 * 视频组mapper
 *
 * @author likaiyan
 * @date 2020/9/1 4:12 下午
 */
@Mapper
public interface VideoGroupMapper extends BaseMapper<VideoGroup> {

    /**
     * 添加视频组类型关联
     *
     * @param videoGroupType 关联实体
     */
    @Insert("insert into video_group_type (group_id, type_id) values (#{videoGroupType.groupId}, #{videoGroupType.typeId})")
    void addVideoGroupType(@Param("videoGroupType") VideoGroupType videoGroupType);

    /**
     * 添加视频组标签关联
     *
     * @param videoGroupTag 关联实体
     */
    @Insert("insert into video_group_tag (group_id, tag_id) values (#{videoGroupTag.groupId}, #{videoGroupTag.tagId})")
    void addVideoGroupTag(@Param("videoGroupTag") VideoGroupTag videoGroupTag);

    /**
     * 删除视频组类型关联
     *
     * @param videoGroupType 关联实体
     * @return 删除条数
     */
    @Delete("delete from video_group_type where  group_id = #{videoGroupType.groupId} and type_id = #{videoGroupType.typeId}")
    int deleteVideoGroupType(@Param("videoGroupType") VideoGroupType videoGroupType);

    /**
     * 删除视频组标签关联
     *
     * @param videoGroupTag 关联实体
     * @return 删除条数
     */
    @Delete("delete from video_group_tag where  group_id = #{videoGroupTag.groupId} and tag_id = #{videoGroupTag.tagId}")
    int deleteVideoGroupTag(@Param("videoGroupTag") VideoGroupTag videoGroupTag);

}