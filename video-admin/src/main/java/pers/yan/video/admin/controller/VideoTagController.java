package pers.yan.video.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.yan.video.admin.pojo.dto.AddVideoTagParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateVideoTagParam;
import pers.yan.video.admin.pojo.entity.VideoTag;
import pers.yan.video.admin.service.VideoTagService;
import pers.yan.video.common.common.ResponseResult;

import javax.validation.Valid;
import java.util.List;

/**
 * 视频标签
 * @author likaiyan
 * @date 2020/9/2 3:21 下午
 */
@RestController
@RequestMapping("/videotag")
public class VideoTagController {

    @Autowired
    private VideoTagService videoTagService;

    @PostMapping("/add")
    public ResponseResult<Integer> addVideoTag(@Valid @RequestBody AddVideoTagParam addVideoTagParam, BindingResult result) {
        return ResponseResult.success(videoTagService.addVideoTag(addVideoTagParam));
    }

    @PostMapping("/update")
    public ResponseResult updateVideoTag(@Valid @RequestBody UpdateVideoTagParam updateVideoTagParam, BindingResult result) {
        videoTagService.updateVideoTag(updateVideoTagParam);
        return ResponseResult.success(null);
    }

    @PostMapping("/delete")
    public ResponseResult deleteVideoTag(@RequestBody Integer typeId) {
        videoTagService.removeById(typeId);
        return ResponseResult.success(null);
    }

    @GetMapping("/list/all")
    public ResponseResult<List<VideoTag>> getAllVideoTag() {
        return ResponseResult.success(videoTagService.list());
    }

    @GetMapping("/list/search")
    public ResponseResult<PageDto<VideoTag>> searchVideoTag(@RequestParam String keyword,
                                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                                  @RequestParam(defaultValue = "30") Integer pageSize) {
        return ResponseResult.success(videoTagService.getVideoTags(keyword, pageNum, pageSize));
    }

    @GetMapping("/{typeId}")
    public ResponseResult<VideoTag> getVideoTag(@PathVariable Integer typeId) {
        return ResponseResult.success(videoTagService.getById(typeId));
    }

}
