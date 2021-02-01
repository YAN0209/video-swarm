package pers.yan.video.admin.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pers.yan.video.admin.dao.*;
import pers.yan.video.admin.pojo.dto.PersonRoleDto;
import pers.yan.video.admin.pojo.dto.RolePermissionRelateDto;
import pers.yan.video.admin.pojo.dto.UserRoleRelateDto;
import pers.yan.video.admin.pojo.dto.VideoGroupRelateDto;
import pers.yan.video.common.exception.ApiRuntimeException;

import java.util.Optional;

/**
 * 检查service类参数正确性
 * @author likaiyan
 * @date 2020/9/2 2:01 下午
 */
@Aspect
@Component
public class ServiceAspect {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private VideoGroupMapper videoGroupMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Autowired
    private VideoTypeMapper videoTypeMapper;

    @Before("execution(* pers.yan.video.admin.service.impl..*(..)) && (args(personRoleDto))")
    public void validatePersonRoleDto(PersonRoleDto personRoleDto) {
        Optional.ofNullable(personMapper.selectById(personRoleDto.getPersonId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.PersonNoFound"));
        Optional.ofNullable(videoGroupMapper.selectById(personRoleDto.getGroupId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.VideoGroupNoFound"));
    }

    @Before("execution(* pers.yan.video.admin.service.impl..*(..)) && (args(userRoleRelateDto))")
    public void validateUserRoleRelateDto(UserRoleRelateDto userRoleRelateDto){
        Optional.ofNullable(userMapper.selectById(userRoleRelateDto.getUserId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.UserNoFound"));
        Optional.ofNullable(roleMapper.selectById(userRoleRelateDto.getRoleId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.RoleNoFound"));
    }

    @Before("execution(* pers.yan.video.admin.service.impl..*(..)) && (args(rolePermissionRelateDto))")
    public void validateRolePermissionRelateDto(RolePermissionRelateDto rolePermissionRelateDto){
        Optional.ofNullable(roleMapper.selectById(rolePermissionRelateDto.getRoleId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.RoleNoFound"));
        Optional.ofNullable(permissionMapper.selectById(rolePermissionRelateDto.getPermissionId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.PermissionNoFound"));
    }

    @Before("execution(* pers.yan.video.admin.service.impl..*(..)) && (args(videoGroupRelateDto))")
    public void validateVideoGroupRelateDto(VideoGroupRelateDto videoGroupRelateDto){
        Optional.ofNullable(videoGroupMapper.selectById(videoGroupRelateDto.getGroupId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoGroupNoFound"));
        Optional.ofNullable(videoTypeMapper.selectById(videoGroupRelateDto.getRelateId()))
                .orElseThrow(() -> new ApiRuntimeException("exception.videoTagOrTypeNoFound"));
        personMapper.
    }

}
