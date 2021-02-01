package pers.yan.video.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.yan.video.admin.pojo.entity.VideoType;

import java.util.List;

/**
 * @author likaiyan
 * @date 2020/9/2 2:32 下午
 */
@Mapper
public interface VideoTypeMapper extends BaseMapper<VideoType> {

    /**
     * 根据视频组获取视频类型
     * @param groupId 视频组
     * @return 视频类型列表
     */
    @Select("select t.* from video_group_type vgt left join video_type t on vgt.type_id = t.id where vgt.group_id = #{groupId}")
    List<VideoType> getVideoTypeByVideoGroup(@Param("groupId") int groupId);

}