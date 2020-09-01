package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author likaiyan
 * @date 2020/6/26 11:06 上午
 */
@Data
public class AddPermissionParam {

    /**
     * 资源名称
     */
    @NotBlank
    private String name;

    /**
     * 资源路径
     */
    @NotBlank
    private String uri;

}
