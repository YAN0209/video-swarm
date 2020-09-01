package pers.yan.video.security.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录参数
 * @author likaiyan
 * @date 2020/8/28 6:36 下午
 */
@Data
public class LoginParam {

    /**
     * 登录名
     */
    @NotBlank
    private String loginName;

    /**
     * 密码
     */
    @NotBlank
    private String password;

}
