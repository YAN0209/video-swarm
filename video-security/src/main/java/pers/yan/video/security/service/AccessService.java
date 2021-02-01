package pers.yan.video.security.service;

import pers.yan.video.security.pojo.entity.Permission;
import pers.yan.video.security.pojo.entity.Role;

import java.util.List;

/**
 * 权限service
 * @author likaiyan
 * @date 2020/9/1 3:37 下午
 */
public interface AccessService {

    /**
     * 获取角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<Role> getRoles(int userId);

    /**
     * 获取用户权限
     *
     * @param userId 用户id
     * @return 权限列表
     */
    List<Permission> getPermissionsByUser(int userId);

    /**
     * 获取角色权限
     *
     * @param roleId 角色id
     * @return 权限列表
     */
    List<Permission> getPermissionsByRole(int roleId);

}