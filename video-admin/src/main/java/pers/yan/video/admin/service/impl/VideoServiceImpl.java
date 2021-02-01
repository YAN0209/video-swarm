package pers.yan.video.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.yan.video.admin.dao.VideoMapper;
import pers.yan.video.admin.pojo.dto.AddVideoParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateVideoParam;
import pers.yan.video.admin.pojo.entity.Video;
import pers.yan.video.admin.service.VideoService;
import pers.yan.video.common.exception.ApiRuntimeException;

/**
 * @author likaiyan
 * @date 2020/9/1 4:10 下午
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public int addVideo(AddVideoParam addVideoParam) {
        Video video = new Video();
        BeanUtils.copyProperties(addVideoParam, video);
        this.save(video);
        return video.getId();
    }

    @Override
    public PageDto<Video> getVideos(Integer groupId, Integer pageNum, Integer pageSize) {
        QueryWrapper<Video> query = new QueryWrapper<>();
        query.eq("group_id", groupId);
        Page<Video> page = new Page<>(pageNum, pageSize);
        this.page(page, query);
        PageDto<Video> pageDto = new PageDto<>();
        BeanUtils.copyProperties(page, pageDto);
        return pageDto;
    }

    @Override
    public void updateVideo(UpdateVideoParam updateVideoParam) {
        Video video = this.getById(updateVideoParam.getId());
        if(video == null){
            throw new ApiRuntimeException("exception.VideoNoFound");
        }
        BeanUtils.copyProperties(updateVideoParam, video);
        this.updateById(video);
    }
}
