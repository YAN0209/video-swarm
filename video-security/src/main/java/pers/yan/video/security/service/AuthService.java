package pers.yan.video.security.service;

import pers.yan.video.security.pojo.dto.LoginParam;
import pers.yan.video.security.pojo.dto.ModifyParam;
import pers.yan.video.security.pojo.dto.RegisterParam;
import pers.yan.video.security.pojo.dto.ValidateParam;

import javax.servlet.http.HttpServletRequest;

/**
 * 角色权限相关service
 *
 * @author likaiyan
 * @date 2020/8/31 1:56 下午
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param loginParam 参数
     * @return token
     */
    String login(LoginParam loginParam);

    /**
     * 注册
     *
     * @param registerParam 参数
     * @return 用户id
     */
    int register(RegisterParam registerParam);

    /**
     * 验证码
     * @param validateParam 校验参数
     * @return boolean
     */
    boolean validateCode(ValidateParam validateParam);

    /**
     * 修改信息
     *
     * @param modifyParam 参数
     */
    void modify(ModifyParam modifyParam);

    /**
     * 刷新token
     *
     * @param request 请求
     * @return token
     */
    String refreshToken(HttpServletRequest request);

}
