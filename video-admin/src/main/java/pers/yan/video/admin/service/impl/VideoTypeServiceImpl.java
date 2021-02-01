package pers.yan.video.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.yan.video.admin.dao.VideoTypeMapper;
import pers.yan.video.admin.pojo.dto.AddVideoTypeParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateVideoTypeParam;
import pers.yan.video.admin.pojo.entity.VideoType;
import pers.yan.video.admin.service.VideoTypeService;

import java.util.List;

/**
 * @author likaiyan
 * @date 2020/9/2 3:00 下午
 */
@Service
public class VideoTypeServiceImpl extends ServiceImpl<VideoTypeMapper, VideoType> implements VideoTypeService {

    @Override
    public int addVideoType(AddVideoTypeParam addVideoTypeParam) {
        VideoType videoType = new VideoType();
        BeanUtils.copyProperties(addVideoTypeParam, videoType);
        this.save(videoType);
        return videoType.getId();
    }

    @Override
    public void updateVideoType(UpdateVideoTypeParam updateVideoTypeParam) {
        VideoType videoType = new VideoType();
        BeanUtils.copyProperties(updateVideoTypeParam, videoType);
        this.updateById(videoType);
    }

    @Override
    public PageDto<VideoType> getVideoTypes(String keyword, Integer pageNum, Integer pageSize) {
        QueryWrapper<VideoType> query = new QueryWrapper<>();
        if (keyword != null) {
            query.like("name", keyword);
        }
        Page<VideoType> page = new Page<>(pageNum, pageSize);
        this.page(page, query);
        PageDto<VideoType> pageDto = new PageDto<>();
        BeanUtils.copyProperties(page, pageDto);
        return pageDto;
    }

    @Override
    public List<VideoType> getVideoTypeByGroupId(Integer groupId) {
        return this.getBaseMapper().getVideoTypeByVideoGroup(groupId);
    }
}
