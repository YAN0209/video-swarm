package pers.yan.video.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.yan.video.admin.pojo.dto.AddVideoParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateVideoParam;
import pers.yan.video.admin.pojo.entity.Video;

/**
 * 视频
 *
 * @author likaiyan
 * @date 2020/9/1 4:03 下午
 */
public interface VideoService extends IService<Video> {

    /**
     * 添加视频
     *
     * @param addVideoParam 视频传输类
     * @return 视频id
     */
    int addVideo(AddVideoParam addVideoParam);

    /**
     * 获取视频
     *
     * @param groupId  视频组id
     * @param pageNum  页码
     * @param pageSize 每页数据量
     * @return 视频列表
     */
    PageDto<Video> getVideos(Integer groupId, Integer pageNum, Integer pageSize);

    /**
     * 更新视频
     *
     * @param updateVideoParam 视频传输类
     */
    void updateVideo(UpdateVideoParam updateVideoParam);

}
