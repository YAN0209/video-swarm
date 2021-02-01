package pers.yan.video.admin.service;

import pers.yan.video.admin.pojo.dto.AddVideoTypeParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateVideoTypeParam;
import pers.yan.video.admin.pojo.entity.VideoType;

import java.util.List;

/**
 * 视频类型
 * @author likaiyan
 * @date 2020/9/2 9:42 上午
 */
public interface VideoTypeService {

    /**
     * 添加视频分类
     * @param addVideoTypeParam 视频分类传输类
     * @return 分类id
     */
    int addVideoType(AddVideoTypeParam addVideoTypeParam);

    /**
     * 更新视频分类
     * @param updateVideoTypeParam 视频传输类
     */
    void updateVideoType(UpdateVideoTypeParam updateVideoTypeParam);

    /**
     * 获取视频类型
     * @param keyword 关键字
     * @param pageNum 页码
     * @param pageSize 每页数据量
     * @return 视频类型列表
     */
    PageDto<VideoType> getVideoTypes(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据视频组id获取视频类型
     * @param groupId 视频组id
     * @return 视频类型列表
     */
    List<VideoType> getVideoTypeByGroupId(Integer groupId);
}