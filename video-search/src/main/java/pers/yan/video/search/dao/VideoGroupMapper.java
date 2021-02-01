package pers.yan.video.search.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.yan.video.search.pojo.entity.VideoGroup;
import pers.yan.video.search.pojo.entity.VideoTag;
import pers.yan.video.search.pojo.entity.VideoType;

import java.util.List;

/**
 * @author likaiyan
 * @date 2020/10/12 11:31 上午
 */
@Mapper
public interface VideoGroupMapper extends BaseMapper<VideoGroup> {

    /**
     * 根据视频组id获取视频标签
     *
     * @param groupId 视频组id
     * @return 视频标签
     */
    @Select("select vt.* from video_tag vt " +
            " left join video_group_tag vgt on vt.id = vgt.tag_id " +
            " where vgt.group_id = #{groupId}")
    List<VideoTag> getVideoTagByVideoGroupId(int groupId);

    /**
     * 根据视频组id获取视频类型
     *
     * @param groupId 视频组id
     * @return 视频类型
     */
    @Select("select vt.* from video_type vt " +
            " left join video_group_type vgt on vt.id = vgt.type_id " +
            " where vgt.group_id = #{groupId}")
    List<VideoType> getVideoTypeByVideoGroupId(int groupId);

    /**
     * 根据人员id获取相关视频id
     *
     * @param id 人员id
     * @return 视频id列表
     */
    @Select("select distinct(t.id) from " +
            " ( select w.group_id from writer w where w.person_id = #{id} " +
            " union all " +
            " select a.group_id from actor a where a.person_id = #{id} " +
            " union all " +
            " select d.group_id from director d where d.person_id = #{id} ) t ")
    List<Integer> getVideoGroupIdByPersonId(int id);

    /**
     * 根据视频标签id获取视频id
     *
     * @param id 视频标签id
     * @return 视频id列表
     */
    @Select("select vgt.group_id from video_group_tag vgt where vgt.tag_id = #{id}")
    List<Integer> getVideoGroupIdByTagId(int id);

    /**
     * 根据视频类型id获取视频id
     *
     * @param id 类型id
     * @return 视频id列表
     */
    @Select("select vgt.group_id from video_group_type vgt where vgt.type_id = #{id}")
    List<Integer> getVideoGroupIdByTypeId(int id);

}