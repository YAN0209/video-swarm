package pers.yan.video.admin.service;

import pers.yan.video.admin.pojo.dto.RolePermissionRelateDto;
import pers.yan.video.admin.pojo.dto.UserRoleRelateDto;

/**
 * 用户权限相关service
 *
 * @author likaiyan
 * @date 2020/8/31 3:40 下午
 */
public interface AccessService {

    /**
     * 添加用户角色
     * @param userRoleRelateDto 参数
     */
    void addUserRole(UserRoleRelateDto userRoleRelateDto);

    /**
     * 添加角色权限
     * @param rolePermissionRelateDto 参数
     */
    void addRolePermission(RolePermissionRelateDto rolePermissionRelateDto);

    /**
     * 删除用户角色
     * @param userRoleRelateDto 参数
     */
    void deleteUserRole(UserRoleRelateDto userRoleRelateDto);

    /**
     * 删除角色权限
     * @param rolePermissionRelateDto 参数
     */
    void deleteRolePermission(RolePermissionRelateDto rolePermissionRelateDto);

}
