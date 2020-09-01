package pers.yan.video.security.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 修改用户信息参数
 *
 * @author likaiyan
 * @date 2020/8/29 8:58 下午
 */
@Data
public class ModifyParam {

    /**
     * 用户id
     */
    @NotEmpty
    private int userId;

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
