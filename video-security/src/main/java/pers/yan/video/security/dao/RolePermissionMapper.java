package pers.yan.video.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.yan.video.security.pojo.entity.RolePermissionRelation;

/**
 * 角色权限关联类mapper
 *
 * @author likaiyan
 * @date 2020/8/31 11:52 上午
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionRelation> {
}