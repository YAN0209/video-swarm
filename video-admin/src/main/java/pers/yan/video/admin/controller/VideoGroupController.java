package pers.yan.video.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.yan.video.admin.pojo.dto.AddVideoGroupParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateVideoGroupParam;
import pers.yan.video.admin.pojo.dto.VideoGroupRelateDto;
import pers.yan.video.admin.pojo.entity.Person;
import pers.yan.video.admin.pojo.entity.VideoGroup;
import pers.yan.video.admin.pojo.entity.VideoTag;
import pers.yan.video.admin.pojo.entity.VideoType;
import pers.yan.video.admin.service.PersonService;
import pers.yan.video.admin.service.VideoGroupService;
import pers.yan.video.admin.service.VideoTagService;
import pers.yan.video.admin.service.VideoTypeService;
import pers.yan.video.common.common.ResponseResult;

import javax.validation.Valid;
import java.util.List;

/**
 * @author likaiyan
 * @date 2020/9/2 3:21 下午
 */
@RestController
@RequestMapping("/videogroup")
public class VideoGroupController {

    @Autowired
    private VideoGroupService videoGroupService;

    @Autowired
    private PersonService personService;

    @Autowired
    private VideoTagService videoTagService;

    @Autowired
    private VideoTypeService videoTypeService;

    @PostMapping("/add")
    public ResponseResult<Integer> addVideoGroup(@Valid @RequestBody AddVideoGroupParam videoGroup, BindingResult result) {
        return ResponseResult.success(videoGroupService.addVideoGroup(videoGroup));
    }

    @PostMapping("/update")
    public ResponseResult updateVideoGroup(@Valid @RequestBody UpdateVideoGroupParam videoGroup, BindingResult result) {
        videoGroupService.updateVideoGroup(videoGroup);
        return ResponseResult.success(null);
    }

    @GetMapping("/list/all")
    public ResponseResult<List<VideoGroup>> getAllVideoGroup() {
        return ResponseResult.success(videoGroupService.list());
    }

    @GetMapping("/list/search")
    public ResponseResult<PageDto<VideoGroup>> searchVideoGroup(@RequestParam String keyword,
                                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                                @RequestParam(defaultValue = "30") Integer pageSize) {
        return ResponseResult.success(videoGroupService.getVideoGroups(keyword, pageNum, pageSize));
    }

    @GetMapping("/{groupId}")
    public ResponseResult<VideoGroup> getVideoGroup(@PathVariable("groupId") Integer groupId) {
        return ResponseResult.success(videoGroupService.getById(groupId));
    }

    @PostMapping("/delete")
    public ResponseResult deleteVideoGroup(@RequestBody Integer groupId) {
        videoGroupService.removeById(groupId);
        return ResponseResult.success(null);
    }

    @GetMapping("/{groupId}/actor")
    public ResponseResult<List<Person>> getActorByGroupId(@PathVariable Integer groupId) {
        return ResponseResult.success(personService.getActorByVideoGroupId(groupId));
    }

    @GetMapping("/{groupId}/writer")
    public ResponseResult<List<Person>> getWriterByGroupId(@PathVariable Integer groupId) {
        return ResponseResult.success(personService.getWriterByVideoGroupId(groupId));
    }

    @GetMapping("/{groupId}/director")
    public ResponseResult<List<Person>> getDirectorByGroupId(@PathVariable Integer groupId) {
        return ResponseResult.success(personService.getDirectorByVideoGroupId(groupId));
    }

    @GetMapping("/{groupId}/videotag")
    public ResponseResult<List<VideoTag>> getVideoTagByGroupId(@PathVariable Integer groupId) {
        return ResponseResult.success(videoTagService.getVideoTagByGroupId(groupId));
    }

    @PostMapping("/videotag/add")
    public ResponseResult addVideoGroupTag(@Valid @RequestBody VideoGroupRelateDto videoGroupRelateDto, BindingResult result){
        videoGroupService.addVideoGroupTag(videoGroupRelateDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/videotag/delete")
    public ResponseResult deleteVideoGroupTag(@Valid @RequestBody VideoGroupRelateDto videoGroupRelateDto, BindingResult result){
        videoGroupService.deleteVideoGroupTag(videoGroupRelateDto);
        return ResponseResult.success(null);
    }

    @GetMapping("/{groupId}/videotype")
    public ResponseResult<List<VideoType>> getVideoTypeByGroupId(@PathVariable Integer groupId) {
        return ResponseResult.success(videoTypeService.getVideoTypeByGroupId(groupId));
    }

    @PostMapping("/videotype/add")
    public ResponseResult addVideoGroupType(@Valid @RequestBody VideoGroupRelateDto videoGroupRelateDto, BindingResult result){
        videoGroupService.addVideoGroupType(videoGroupRelateDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/videotype/delete")
    public ResponseResult deleteVideoGroupType(@Valid @RequestBody VideoGroupRelateDto videoGroupRelateDto, BindingResult result){
        videoGroupService.deleteVideoGroupType(videoGroupRelateDto);
        return ResponseResult.success(null);
    }

}
