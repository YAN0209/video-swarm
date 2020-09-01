package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author likaiyan
 * @date 2020/6/26 11:06 上午
 */
@Data
public class UpdatePermissionParam extends AddPermissionParam {

    /**
     * 主键
     */
    @NotNull
    private Integer id;

}
