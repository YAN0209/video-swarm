package pers.yan.video.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.yan.video.common.common.ResponseResult;
import pers.yan.video.security.pojo.entity.Permission;
import pers.yan.video.security.pojo.entity.Role;
import pers.yan.video.security.service.AccessService;

import java.util.List;

/**
 * 获取用户权限controller
 * @author likaiyan
 * @date 2020/9/1 3:40 下午
 */
@RestController
@RequestMapping("/access")
public class AccessController {
    
    @Autowired
    private AccessService accessService;
    
    /**
     * 获取用户角色
     * @param userId 用户id
     * @return 角色列表
     */
    @GetMapping("/user/{userId}/roles")
    public ResponseResult<List<Role>> getRoles(@PathVariable int userId) {
        return ResponseResult.success(accessService.getRoles(userId));
    }

    /**
     * 获取用户权限
     * @param userId 用户id
     * @return 权限列表
     */
    @GetMapping("/user/{userId}/permissions")
    public ResponseResult<List<Permission>> getPermissionsByUser(@PathVariable int userId) {
        return ResponseResult.success(accessService.getPermissionsByUser(userId));
    }

    /**
     * 获取角色权限
     * @param roleId 角色id
     * @return 权限列表
     */
    @GetMapping("/role/{roleId}/permissions")
    public ResponseResult<List<Permission>> getPermissionsByRole(@PathVariable int roleId) {
        return ResponseResult.success(accessService.getPermissionsByRole(roleId));
    }
}
