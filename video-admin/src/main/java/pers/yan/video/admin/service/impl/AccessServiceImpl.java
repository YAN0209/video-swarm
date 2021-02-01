package pers.yan.video.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yan.video.admin.dao.*;
import pers.yan.video.admin.pojo.dto.RolePermissionRelateDto;
import pers.yan.video.admin.pojo.dto.UserRoleRelateDto;
import pers.yan.video.admin.pojo.entity.RolePermissionRelation;
import pers.yan.video.admin.pojo.entity.UserRoleRelation;
import pers.yan.video.admin.service.AccessService;

/**
 * 用户权限相关service实现
 *
 * @author likaiyan
 * @date 2020/8/31 3:42 下午
 */
@Service
public class AccessServiceImpl implements AccessService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public void addUserRole(UserRoleRelateDto userRoleRelateDto) {
        UserRoleRelation userRoleRelation = new UserRoleRelation();
        BeanUtils.copyProperties(userRoleRelateDto, userRoleRelation);
        userRoleMapper.insert(userRoleRelation);
    }

    @Override
    public void addRolePermission(RolePermissionRelateDto rolePermissionRelateDto) {
        RolePermissionRelation rolePermissionRelation = new RolePermissionRelation();
        BeanUtils.copyProperties(rolePermissionRelateDto, rolePermissionRelation);
        rolePermissionMapper.insert(rolePermissionRelation);
    }

    @Override
    public void deleteUserRole(UserRoleRelateDto userRoleRelateDto) {
        QueryWrapper<UserRoleRelation> query = new QueryWrapper<>();
        query.eq("userId", userRoleRelateDto.getUserId())
                .eq("roleId", userRoleRelateDto.getRoleId());
        userRoleMapper.delete(query);
    }

    @Override
    public void deleteRolePermission(RolePermissionRelateDto rolePermissionRelateDto) {
        QueryWrapper<RolePermissionRelation> query = new QueryWrapper<>();
        query.eq("roleId", rolePermissionRelateDto.getRoleId())
                .eq("permissionId", rolePermissionRelateDto.getPermissionId());
        rolePermissionMapper.delete(query);
    }
}
