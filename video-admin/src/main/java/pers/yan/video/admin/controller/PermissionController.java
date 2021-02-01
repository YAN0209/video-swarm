package pers.yan.video.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.yan.video.admin.pojo.dto.AddPermissionParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdatePermissionParam;
import pers.yan.video.admin.pojo.entity.Permission;
import pers.yan.video.admin.service.PermissionService;
import pers.yan.video.common.common.ResponseResult;

import javax.validation.Valid;
import java.util.List;

/**
 * 权限controller
 *
 * @author likaiyan
 * @date 2020/8/27 6:00 下午
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    
    @PostMapping("/permission/add")
    public ResponseResult<Integer> addPermission(@Valid @RequestBody AddPermissionParam addPermissionParam, BindingResult result) {
        return ResponseResult.success(permissionService.addPermission(addPermissionParam));
    }

    @PostMapping("/permission/delete")
    public ResponseResult deletePermission(@RequestParam Integer permissionId) {
        permissionService.removeById(permissionId);
        return ResponseResult.success(null);
    }

    @PostMapping("/permission/update")
    public ResponseResult updatePermission(@Valid @RequestBody UpdatePermissionParam updatePermissionParam, BindingResult result) {
        permissionService.updatePermission(updatePermissionParam);
        return ResponseResult.success(null);
    }

    @GetMapping("/permission/list/all")
    public ResponseResult<List<Permission>> getAllPermission(){
        return ResponseResult.success(permissionService.list());
    }

    @GetMapping("/permission/list/search")
    public ResponseResult<PageDto<Permission>> searchPermission(@RequestParam(required = false) String keyword,
                                                                @RequestParam(defaultValue = "1") Integer pageNum,
                                                                @RequestParam(defaultValue = "30") Integer pageSize){
        return ResponseResult.success(permissionService.searchPermission(keyword, pageNum, pageSize));
    }

    @GetMapping("/permission/{permissionId}")
    public ResponseResult<Permission> getPermission(@PathVariable Integer permissionId){
        return ResponseResult.success(permissionService.getById(permissionId));
    }

}
