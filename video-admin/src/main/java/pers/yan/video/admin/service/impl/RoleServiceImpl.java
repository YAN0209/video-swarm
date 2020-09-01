package pers.yan.video.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yan.video.admin.dao.RoleMapper;
import pers.yan.video.admin.dao.UserMapper;
import pers.yan.video.admin.dao.UserRoleMapper;
import pers.yan.video.admin.pojo.dto.AddRoleParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateRoleParam;
import pers.yan.video.admin.pojo.dto.UserRoleRelateDto;
import pers.yan.video.admin.pojo.entity.Role;
import pers.yan.video.admin.pojo.entity.UserRoleRelation;
import pers.yan.video.admin.service.RoleService;
import pers.yan.video.common.exception.ApiRuntimeException;

import java.util.Optional;

/**
 * 角色service
 *
 * @author likaiyan
 * @date 2020/8/27 5:35 下午
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int addRole(AddRoleParam addRoleParam) {
        Role role = new Role();
        BeanUtils.copyProperties(addRoleParam, role);
        this.save(role);
        return role.getId();
    }

    @Override
    public void updateRole(UpdateRoleParam updateRoleParam) {
        Role role = new Role();
        BeanUtils.copyProperties(updateRoleParam, role);
        this.updateById(role);
    }

    @Override
    public PageDto<Role> searchRole(String keyword, int pageNum, int pageSize) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        wrapper.like("name", keyword);
        Page<Role> page = new Page<>(pageNum, pageSize);
        this.page(page, wrapper);
        PageDto<Role> pageDto = new PageDto<>();
        BeanUtils.copyProperties(page, pageDto);
        return pageDto;
    }

    @Override
    public void addUserRole(UserRoleRelateDto userRoleRelateDto) {
        Optional.ofNullable(userMapper.selectById(userRoleRelateDto.getUserId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.UserNoFound"));
        Optional.ofNullable(this.getById(userRoleRelateDto.getRoleId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.RoleNoFound"));
        UserRoleRelation userRoleRelation = new UserRoleRelation();
        BeanUtils.copyProperties(userRoleRelateDto, userRoleRelation);
        userRoleMapper.insert(userRoleRelation);
    }

}
