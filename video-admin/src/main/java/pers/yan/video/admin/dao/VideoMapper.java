package pers.yan.video.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.yan.video.admin.pojo.entity.Video;

/**
 * 视频mapper
 *
 * @author likaiyan
 * @date 2020/9/1 4:11 下午
 */
@Mapper
public interface VideoMapper extends BaseMapper<Video> {
}