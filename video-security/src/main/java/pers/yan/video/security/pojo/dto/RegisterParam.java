package pers.yan.video.security.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 注册参数
 *
 * @author likaiyan
 * @date 2020/6/26 10:58 上午
 */
@Data
public class RegisterParam {

    /**
     * 用户名
     */
    @NotBlank
    private String username;

    /**
     * 密码
     */
    @NotBlank
    private String password;

    /**
     * 登陆名
     */
    @NotBlank
    private String loginName;

    /**
     * 电子邮件
     */
    @NotBlank
    private String email;

}
