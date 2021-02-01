package pers.yan.video.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.yan.video.admin.pojo.dto.AddVideoGroupParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateVideoGroupParam;
import pers.yan.video.admin.pojo.dto.VideoGroupRelateDto;
import pers.yan.video.admin.pojo.entity.VideoGroup;

/**
 * 视频组
 * @author likaiyan
 * @date 2020/9/1 4:05 下午
 */
public interface VideoGroupService extends IService<VideoGroup> {

    /**
     * 增加视频组
     *
     * @param addVideoGroupParam 视频组传输类
     * @return 视频组id
     */
    int addVideoGroup(AddVideoGroupParam addVideoGroupParam);

    /**
     * 获取视频组列表
     *
     * @param keyword  关键字
     * @param pageNum  页码
     * @param pageSize 每页数据量
     * @return 视频组列表
     */
    PageDto<VideoGroup> getVideoGroups(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 更新视频组
     *
     * @param updateVideoGroupParam 视频组传输类
     */
    void updateVideoGroup(UpdateVideoGroupParam updateVideoGroupParam);

    /**
     * 视频组关联视频类型
     * @param videoGroupRelateDto 关联传输类
     */
    void addVideoGroupType(VideoGroupRelateDto videoGroupRelateDto);

    /**
     * 视频组删除关联视频类型
     * @param videoGroupRelateDto 关联传输类
     */
    void deleteVideoGroupType(VideoGroupRelateDto videoGroupRelateDto);

    /**
     * 视频组关联视频标签
     * @param videoGroupRelateDto 关联传输类
     */
    void addVideoGroupTag(VideoGroupRelateDto videoGroupRelateDto);

    /**
     * 视频组删除关联视频标签
     * @param videoGroupRelateDto 关联传输类
     */
    void deleteVideoGroupTag(VideoGroupRelateDto videoGroupRelateDto);

}