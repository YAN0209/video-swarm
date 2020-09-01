package pers.yan.video.security.service;

import pers.yan.video.security.pojo.dto.LoginParam;
import pers.yan.video.security.pojo.dto.ModifyParam;
import pers.yan.video.security.pojo.dto.RegisterParam;
import pers.yan.video.security.pojo.entity.Permission;
import pers.yan.video.security.pojo.entity.Role;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 角色权限相关service
 *
 * @author likaiyan
 * @date 2020/8/31 1:56 下午
 */
public interface AuthService {

    /**
     * 获取角色
     *
     * @param userId 用户id
     * @return 角色列表
     */
    List<Role> getRoles(int userId);

    /**
     * 获取用户权限
     *
     * @param userId 用户id
     * @return 权限列表
     */
    List<Permission> getPermissionsByUser(int userId);

    /**
     * 获取角色权限
     *
     * @param roleId 角色id
     * @return 权限列表
     */
    List<Permission> getPermissionsByRole(int roleId);

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
