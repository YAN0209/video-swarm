package pers.yan.video.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import pers.yan.video.common.utils.JwtTokenUtil;
import pers.yan.video.security.dao.*;
import pers.yan.video.security.pojo.dto.LoginParam;
import pers.yan.video.security.pojo.dto.ModifyParam;
import pers.yan.video.security.pojo.dto.RegisterParam;
import pers.yan.video.security.pojo.entity.*;
import pers.yan.video.security.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author likaiyan
 * @date 2020/8/31 2:22 下午
 */
@Service
public class AuthServiceImpl extends ServiceImpl<UserMapper, User> implements AuthService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<Role> getRoles(int userId) {
        List<Integer> roleIds = getRoleIds(userId);
        if (!CollectionUtils.isEmpty(roleIds)) {
            return roleMapper.selectBatchIds(roleIds);
        }
        return null;
    }

    @Override
    public List<Permission> getPermissionsByUser(int userId) {
        List<Permission> permissions = new ArrayList<>();
        List<Integer> roleIds = getRoleIds(userId);
        if (!CollectionUtils.isEmpty(roleIds)) {
            roleIds.forEach(roleId -> permissions.addAll(getPermissionsByRole(roleId)));
        }
        return permissions;
    }

    @Override
    public List<Permission> getPermissionsByRole(int roleId) {
        List<Permission> permissions = new ArrayList<>();
        QueryWrapper<RolePermissionRelation> query = new QueryWrapper<>();
        List<RolePermissionRelation> rolePermissionRelations = rolePermissionMapper.selectList(query);
        if (!CollectionUtils.isEmpty(rolePermissionRelations)) {
            rolePermissionRelations.forEach(rolePermissionRelation ->
                    permissions.add(permissionMapper.selectById(rolePermissionRelation.getPermissionId())));
        }
        return permissions;
    }

    @Override
    public String login(LoginParam loginParam) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("loginName", loginParam.getLoginName())
                .eq("password", loginParam.getPassword());
        User user = this.getOne(wrapper);
        if (user != null) {
            return jwtTokenUtil.generateToken(user.getId());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(RegisterParam registerParam) {
        User user = new User();
        BeanUtils.copyProperties(registerParam, user);
        this.save(user);
        return user.getId();
    }

    @Override
    public void modify(ModifyParam modifyParam) {
        User user = this.getById(modifyParam.getUserId());
        BeanUtils.copyProperties(modifyParam, user);
        this.updateById(user);
    }

    @Override
    public String refreshToken(HttpServletRequest request) {
        String token = jwtTokenUtil.getToken(request);
        if (!StringUtils.isEmpty(token) && jwtTokenUtil.validateToken(token)) {
            return jwtTokenUtil.generateToken(jwtTokenUtil.getUserIdFromToken(token));
        }
        return null;
    }

    /**
     * 获取某个用户的所有角色id
     *
     * @param userId 用户id
     * @return 角色ids
     */
    private List<Integer> getRoleIds(int userId) {
        QueryWrapper<UserRoleRelation> query = new QueryWrapper<>();
        query.eq("user_id", userId);
        List<UserRoleRelation> userRoleRelations = userRoleMapper.selectList(query);
        if (!CollectionUtils.isEmpty(userRoleRelations)) {
            return userRoleRelations.stream().map(UserRoleRelation::getRoleId).collect(Collectors.toList());
        }
        return null;
    }
}
