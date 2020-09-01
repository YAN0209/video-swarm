package pers.yan.video.admin.common;

import lombok.Getter;

/**
 * 通用返回状态
 *
 * @author likaiyan
 * @date 2020/4/1 9:55 上午
 */
@Getter
public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS(200, "response.success"),
    /**
     * 失败
     */
    FAILED(500, "response.failed"),
    /**
     * 参数错误
     */
    VALIDATE_FAILED(400, "response.validateFailed"),
    /**
     * 未登录
     */
    UNAUTHORIZED(401, "response.unauthorized"),
    /**
     * 无权限
     */
    FORBIDDEN(403, "response.forbidden");

    private long code;

    private String message;

    ResponseCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

}
