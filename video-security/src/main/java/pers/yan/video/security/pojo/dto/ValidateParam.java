package pers.yan.video.security.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 验证码
 *
 * @author likaiyan
 * @date 2020/9/29 4:33 下午
 */
@Data
public class ValidateParam {

    @NotBlank
    private String email;

    @NotBlank
    private String validateCode;

    public ValidateParam() {
    }
}
