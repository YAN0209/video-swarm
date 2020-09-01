package pers.yan.video.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.yan.video.common.common.ResponseResult;
import pers.yan.video.security.pojo.dto.LoginParam;
import pers.yan.video.security.pojo.dto.ModifyParam;
import pers.yan.video.security.pojo.dto.RegisterParam;
import pers.yan.video.security.pojo.entity.Permission;
import pers.yan.video.security.pojo.entity.Role;
import pers.yan.video.security.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * 权限相关接口
 *
 * @author likaiyan
 * @date 2020/8/31 3:54 下午
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 登录接口
     * @param loginParam 参数
     * @param result 参数校验结果
     * @return token
     */
    @PostMapping("/login")
    public ResponseResult<String> login(@RequestBody @Valid LoginParam loginParam, BindingResult result){
        String token = authService.login(loginParam);
        if(StringUtils.isEmpty(token)){
            return ResponseResult.validateFailed();
        }
        return ResponseResult.success(token);
    }

    /**
     * 注册
     * @param registerParam 参数
     * @param result 参数校验结果
     * @return /
     */
    @PostMapping("/register")
    public ResponseResult<Integer> register(@RequestBody @Valid RegisterParam registerParam, BindingResult result){
        return ResponseResult.success(authService.register(registerParam));
    }

    /**
     * 修改用户信息
     * @param modifyParam 参数
     * @param result 参数校验结果
     * @return /
     */
    @PostMapping("/modify")
    public ResponseResult modify(@RequestBody @Valid ModifyParam modifyParam, BindingResult result){
        authService.modify(modifyParam);
        return ResponseResult.success(null);
    }

    /**
     * 刷新token
     * @param request 请求
     * @return token
     */
    @GetMapping("/refreshtoken")
    public ResponseResult<String> refreshToken(HttpServletRequest request){
        return ResponseResult.success(authService.refreshToken(request));
    }

    /**
     * 获取用户角色
     * @param userId 用户id
     * @return 角色列表
     */
    @GetMapping("/user/{userId}/roles")
    public ResponseResult<List<Role>> getRoles(@PathVariable int userId) {
        return ResponseResult.success(authService.getRoles(userId));
    }

    /**
     * 获取用户权限
     * @param userId 用户id
     * @return 权限列表
     */
    @GetMapping("/user/{userId}/permissions")
    public ResponseResult<List<Permission>> getPermissionsByUser(@PathVariable int userId) {
        return ResponseResult.success(authService.getPermissionsByUser(userId));
    }

    /**
     * 获取角色权限
     * @param roleId 角色id
     * @return 权限列表
     */
    @GetMapping("/role/{roleId}/permissions")
    public ResponseResult<List<Permission>> getPermissionsByRole(@PathVariable int roleId) {
        return ResponseResult.success(authService.getPermissionsByRole(roleId));
    }

}
