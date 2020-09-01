package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/26 11:08 上午
 */
@Data
public class RolePermissionRelateDto {

    /**
     * 角色id
     */
    @NotNull
    private Integer roleId;

    /**
     * 权限id
     */
    @NotNull
    private Integer permissionId;

}
