package pers.yan.video.security.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/26 11:07 上午
 */
@Data
public class UserRoleRelateDto {

    /**
     * 用户id
     */
    @NotNull
    private Integer userId;

    /**
     * 角色id
     */
    @NotNull
    private Integer roleId;

}
