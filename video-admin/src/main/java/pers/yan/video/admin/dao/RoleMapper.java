package pers.yan.video.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.yan.video.admin.pojo.entity.Role;

/**
 * 角色mapper
 *
 * @author likaiyan
 * @date 2020/8/27 5:36 下午
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
}
