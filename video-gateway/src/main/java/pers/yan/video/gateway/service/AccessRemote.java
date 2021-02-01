package pers.yan.video.gateway.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pers.yan.video.common.common.ResponseResult;
import pers.yan.video.gateway.pojo.entity.Permission;
import pers.yan.video.gateway.pojo.entity.Role;
import pers.yan.video.gateway.service.fallback.AccessRemoteFallBack;

import java.util.List;

/**
 * 远程调用security接口
 *
 * @author likaiyan
 * @date 2020/9/1 9:51 上午
 */
@FeignClient(value = "video-security", fallback = AccessRemoteFallBack.class)
@RequestMapping("/access")
public interface AccessRemote {

    /**
     * 获取用户角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    @GetMapping("/user/{userId}/roles")
    ResponseResult<List<Role>> getRoles(@PathVariable("userId") int userId);

    /**
     * 获取用户权限
     *
     * @param userId 用户id
     * @return 权限列表
     */
    @GetMapping("/user/{userId}/permissions")
    ResponseResult<List<Permission>> getPermissionsByUser(@PathVariable("userId") int userId);

    /**
     * 获取角色权限
     *
     * @param roleId 角色id
     * @return 权限列表
     */
    @GetMapping("/role/{roleId}/permissions")
    ResponseResult<List<Permission>> getPermissionsByRole(@PathVariable("roleId") int roleId);

}