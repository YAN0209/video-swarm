package pers.yan.video.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.yan.video.admin.pojo.dto.AddPermissionParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.RolePermissionRelateDto;
import pers.yan.video.admin.pojo.dto.UpdatePermissionParam;
import pers.yan.video.admin.pojo.entity.Permission;

/**
 * 权限service
 *
 * @author likaiyan
 * @date 2020/8/27 5:55 下午
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 添加权限
     * @param addPermissionParam 参数
     * @return 权限id
     */
    int addPermission(AddPermissionParam addPermissionParam);

    /**
     * 更新权限
     * @param updatePermissionParam 参数
     */
    void updatePermission(UpdatePermissionParam updatePermissionParam);

    /**
     * 查询权限
     * @param keyword 关键字
     * @param pageNum 页码
     * @param pageSize 条数
     * @return 分页实体
     */
    PageDto<Permission> searchPermission(String keyword, int pageNum, int pageSize);

    /**
     * 添加角色权限
     * @param rolePermissionRelateDto 参数
     * @return id
     */
    void addRolePermission(RolePermissionRelateDto rolePermissionRelateDto);

}