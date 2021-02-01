package pers.yan.video.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.yan.video.admin.pojo.entity.VideoTag;

import java.util.List;

/**
 * @author likaiyan
 * @date 2020/9/2 2:30 下午
 */
@Mapper
public interface VideoTagMapper extends BaseMapper<VideoTag> {

    /**
     * 根据视频组获取视频标签
     * @param groupId 视频组id
     * @return 标签列表
     */
    @Select("select t.* from video_group_tag vgt left join video_tag t on vgt.tag_id = t.id where vgt.group_id = #{groupId}")
    public List<VideoTag> getVideoTagByVideoGroup(@Param("groupId") int groupId);
}