package pers.yan.video.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.yan.video.admin.pojo.dto.AddVideoParam;
import pers.yan.video.admin.pojo.dto.UpdateVideoParam;
import pers.yan.video.admin.service.VideoService;
import pers.yan.video.common.common.ResponseResult;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @author likaiyan
 * @date 2020/9/2 3:20 下午
 */
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping("/add")
    public ResponseResult<Integer> addVideo(@Valid @RequestBody AddVideoParam video, BindingResult result) {
        return ResponseResult.success(videoService.addVideo(video));
    }

    @PostMapping("/update")
    public ResponseResult updateVideo(@Valid @RequestBody UpdateVideoParam video, BindingResult result) {
        videoService.updateVideo(video);
        return ResponseResult.success(null);
    }

    @GetMapping("/group/{groupId}")
    public ResponseResult getVideos(@PathVariable("groupId") Integer groupId,
                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "30") Integer pageSize) {
        return ResponseResult.success(videoService.getVideos(groupId, pageNum, pageSize));
    }

    @GetMapping("/{videoId}")
    public ResponseResult getVideo(@PathVariable("videoId") Integer videoId) {
        return ResponseResult.success(videoService.getById(videoId));
    }

    @PostMapping("/delete")
    public ResponseResult deleteVideo(@NotEmpty @RequestBody Integer videoId) {
        videoService.removeById(videoId);
        return ResponseResult.success(null);
    }

}
