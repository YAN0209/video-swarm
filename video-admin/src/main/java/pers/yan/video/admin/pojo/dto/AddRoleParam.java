package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author likaiyan
 * @date 2020/6/26 11:05 上午
 */
@Data
public class AddRoleParam {

    /**
     * 角色名
     */
    @NotBlank
    private String name;

}
