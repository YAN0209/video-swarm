package pers.yan.video.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pers.yan.video.security.dao.PermissionMapper;
import pers.yan.video.security.dao.RoleMapper;
import pers.yan.video.security.dao.RolePermissionMapper;
import pers.yan.video.security.dao.UserRoleMapper;
import pers.yan.video.security.pojo.entity.Permission;
import pers.yan.video.security.pojo.entity.Role;
import pers.yan.video.security.pojo.entity.RolePermissionRelation;
import pers.yan.video.security.pojo.entity.UserRoleRelation;
import pers.yan.video.security.service.AccessService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author likaiyan
 * @date 2020/9/1 3:38 下午
 */
@Service
public class AccessServiceImpl implements AccessService {

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
