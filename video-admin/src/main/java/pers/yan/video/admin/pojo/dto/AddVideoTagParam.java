package pers.yan.video.admin.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author likaiyan
 * @date 2020/6/13 10:29 下午
 */
@Data
public class AddVideoTagParam {

    /**
     * 标签名
     */
    @NotBlank
    private String name;

    /**
     * 锁定 0未锁定 1已锁定
     */
    private boolean lockTag;

}
