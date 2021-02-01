package pers.yan.video.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.yan.video.admin.pojo.dto.AddVideoTagParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateVideoTagParam;
import pers.yan.video.admin.pojo.entity.VideoTag;

import java.util.List;

/**
 * 视频标签
 * @author likaiyan
 * @date 2020/9/2 9:41 上午
 */
public interface VideoTagService extends IService<VideoTag> {

    /**
     * 添加视频标签
     * @param addVideoTagParam 视频标签传输类
     * @return 标签id
     */
    int addVideoTag(AddVideoTagParam addVideoTagParam);

    /**
     * 更新视频标签
     * @param updateVideoTagParam 视频标签传输类
     */
    void updateVideoTag(UpdateVideoTagParam updateVideoTagParam);


    /**
     * 获取视频标签
     * @param keyword 关键字
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @return 标签列表
     */
    PageDto<VideoTag> getVideoTags(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据视频id获取视频标签
     * @param groupId 视频组id
     * @return 标签列表
     */
    List<VideoTag> getVideoTagByGroupId(Integer groupId);

}