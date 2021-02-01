package pers.yan.video.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.yan.video.admin.dao.VideoGroupMapper;
import pers.yan.video.admin.pojo.dto.AddVideoGroupParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateVideoGroupParam;
import pers.yan.video.admin.pojo.dto.VideoGroupRelateDto;
import pers.yan.video.admin.pojo.entity.VideoGroup;
import pers.yan.video.admin.pojo.entity.VideoGroupTag;
import pers.yan.video.admin.pojo.entity.VideoGroupType;
import pers.yan.video.admin.service.VideoGroupService;
import pers.yan.video.common.exception.ApiRuntimeException;

/**
 * @author likaiyan
 * @date 2020/9/1 5:22 下午
 */
@Service
public class VideoGroupServiceImpl extends ServiceImpl<VideoGroupMapper, VideoGroup> implements VideoGroupService {

    @Override
    public int addVideoGroup(AddVideoGroupParam addVideoGroupParam) {
        VideoGroup videoGroup = new VideoGroup();
        BeanUtils.copyProperties(addVideoGroupParam, videoGroup);
        this.save(videoGroup);
        return videoGroup.getId();
    }

    @Override
    public PageDto<VideoGroup> getVideoGroups(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<VideoGroup> query = new QueryWrapper<>();
        if(keyword != null){
            query.eq("name", keyword);
        }
        Page<VideoGroup> page = new Page<>(pageNum, pageSize);
        this.page(page, query);
        PageDto<VideoGroup> pageDto = new PageDto<>();
        BeanUtils.copyProperties(page, pageDto);
        return pageDto;
    }

    @Override
    public void updateVideoGroup(UpdateVideoGroupParam updateVideoGroupParam) {
        VideoGroup videoGroup = this.getById(updateVideoGroupParam.getId());
        if(videoGroup == null){
            throw new ApiRuntimeException("exception.VideoGroupNoFound");
        }
        BeanUtils.copyProperties(updateVideoGroupParam, videoGroup);
        this.updateById(videoGroup);
    }

    @Override
    public void addVideoGroupType(VideoGroupRelateDto videoGroupRelateDto) {
        VideoGroupType videoGroupType = new VideoGroupType();
        videoGroupType.setTypeId(videoGroupRelateDto.getRelateId());
        videoGroupType.setGroupId(videoGroupRelateDto.getGroupId());
        this.getBaseMapper().addVideoGroupType(videoGroupType);
    }

    @Override
    public void deleteVideoGroupType(VideoGroupRelateDto videoGroupRelateDto) {
        VideoGroupType videoGroupType = new VideoGroupType();
        videoGroupType.setTypeId(videoGroupRelateDto.getRelateId());
        videoGroupType.setGroupId(videoGroupRelateDto.getGroupId());
        this.getBaseMapper().deleteVideoGroupType(videoGroupType);
    }

    @Override
    public void addVideoGroupTag(VideoGroupRelateDto videoGroupRelateDto) {
        VideoGroupTag videoGroupTag = new VideoGroupTag();
        videoGroupTag.setTagId(videoGroupRelateDto.getRelateId());
        videoGroupTag.setGroupId(videoGroupTag.getGroupId());
        this.getBaseMapper().addVideoGroupTag(videoGroupTag);
    }

    @Override
    public void deleteVideoGroupTag(VideoGroupRelateDto videoGroupRelateDto) {
        VideoGroupTag videoGroupTag = new VideoGroupTag();
        videoGroupTag.setTagId(videoGroupRelateDto.getRelateId());
        videoGroupTag.setGroupId(videoGroupTag.getGroupId());
        this.getBaseMapper().deleteVideoGroupTag(videoGroupTag);
    }
}
