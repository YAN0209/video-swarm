package pers.yan.video.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.yan.video.admin.dao.VideoTagMapper;
import pers.yan.video.admin.pojo.dto.AddVideoTagParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateVideoTagParam;
import pers.yan.video.admin.pojo.entity.VideoTag;
import pers.yan.video.admin.service.VideoTagService;

import java.util.List;

/**
 * 视频标签
 * @author likaiyan
 * @date 2020/9/2 2:30 下午
 */
@Service
public class VideoTagServiceImpl extends ServiceImpl<VideoTagMapper, VideoTag> implements VideoTagService {

    @Override
    public int addVideoTag(AddVideoTagParam addVideoTagParam) {
        VideoTag videoTag = new VideoTag();
        BeanUtils.copyProperties(addVideoTagParam, videoTag);
        this.save(videoTag);
        return videoTag.getId();
    }

    @Override
    public void updateVideoTag(UpdateVideoTagParam updateVideoTagParam) {
        VideoTag videoTag = new VideoTag();
        BeanUtils.copyProperties(updateVideoTagParam, videoTag);
        this.updateById(videoTag);
    }

    @Override
    public PageDto<VideoTag> getVideoTags(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<VideoTag> query = new QueryWrapper<>();
        if(keyword != null){
            query.like("name", keyword);
        }
        Page<VideoTag> page = new Page<>(pageNum, pageSize);
        this.page(page, query);
        PageDto<VideoTag> pageDto = new PageDto<>();
        BeanUtils.copyProperties(page, pageDto);
        return pageDto;
    }

    @Override
    public List<VideoTag> getVideoTagByGroupId(Integer groupId) {
        return this.getBaseMapper().getVideoTagByVideoGroup(groupId);
    }
}
