package pers.yan.video.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.yan.video.admin.pojo.entity.RolePermissionRelation;

/**
 * 角色权限关联类mapper
 *
 * @author likaiyan
 * @date 2020/8/31 11:52 上午
 */
@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionRelation> {
}