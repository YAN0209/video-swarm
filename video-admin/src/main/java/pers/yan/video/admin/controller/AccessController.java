package pers.yan.video.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.yan.video.admin.pojo.dto.RolePermissionRelateDto;
import pers.yan.video.admin.pojo.dto.UserRoleRelateDto;
import pers.yan.video.admin.service.AccessService;
import pers.yan.video.common.common.ResponseResult;

import javax.validation.Valid;

/**
 * 用户权限管理controller
 *
 * @author likaiyan
 * @date 2020/9/1 3:56 下午
 */
@RestController
@RequestMapping("/access")
public class AccessController {

    @Autowired
    private AccessService accessService;

    @PostMapping("/userrole/add")
    public ResponseResult addUserRoleRelation(@Valid @RequestBody UserRoleRelateDto userRoleRelateDto, BindingResult result) {
        accessService.addUserRole(userRoleRelateDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/userrole/delete")
    public ResponseResult deleteUserRoleRelation(@Valid @RequestBody UserRoleRelateDto userRoleRelateDto, BindingResult result) {
        accessService.deleteUserRole(userRoleRelateDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/rolepermission/add")
    public ResponseResult addRolePermissionRelation(@Valid @RequestBody RolePermissionRelateDto userRoleRelateDto, BindingResult result) {
        accessService.addRolePermission(userRoleRelateDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/rolepermission/delete")
    public ResponseResult deleteRolePermissionRelation(@Valid @RequestBody RolePermissionRelateDto userRoleRelateDto, BindingResult result) {
        accessService.deleteRolePermission(userRoleRelateDto);
        return ResponseResult.success(null);
    }

}
