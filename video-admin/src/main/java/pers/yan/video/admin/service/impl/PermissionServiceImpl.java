package pers.yan.video.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yan.video.admin.dao.PermissionMapper;
import pers.yan.video.admin.dao.RoleMapper;
import pers.yan.video.admin.dao.RolePermissionMapper;
import pers.yan.video.admin.pojo.dto.AddPermissionParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.RolePermissionRelateDto;
import pers.yan.video.admin.pojo.dto.UpdatePermissionParam;
import pers.yan.video.admin.pojo.entity.Permission;
import pers.yan.video.admin.pojo.entity.RolePermissionRelation;
import pers.yan.video.admin.service.PermissionService;
import pers.yan.video.common.exception.ApiRuntimeException;

import java.util.Optional;

/**
 * 权限service
 *
 * @author likaiyan
 * @date 2020/8/27 6:06 下午
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public int addPermission(AddPermissionParam addPermissionParam) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(addPermissionParam, permission);
        this.save(permission);
        return permission.getId();
    }

    @Override
    public void updatePermission(UpdatePermissionParam updatePermissionParam) {
        Permission permission = new Permission();
        BeanUtils.copyProperties(updatePermissionParam, permission);
        this.updateById(permission);
    }

    @Override
    public PageDto<Permission> searchPermission(String keyword, int pageNum, int pageSize) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        wrapper.like("name", keyword);
        Page<Permission> page = new Page<>(pageNum, pageSize);
        this.page(page, wrapper);
        PageDto<Permission> pageDto = new PageDto<>();
        BeanUtils.copyProperties(page, pageDto);
        return pageDto;
    }

    @Override
    public void addRolePermission(RolePermissionRelateDto rolePermissionRelateDto) {
        Optional.ofNullable(roleMapper.selectById(rolePermissionRelateDto.getRoleId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.RoleNoFound"));
        Optional.ofNullable(this.getById(rolePermissionRelateDto.getPermissionId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.PermissionNoFound"));
        RolePermissionRelation rolePermissionRelation = new RolePermissionRelation();
        BeanUtils.copyProperties(rolePermissionRelateDto, rolePermissionRelation);
        rolePermissionMapper.insert(rolePermissionRelation);
    }
}
