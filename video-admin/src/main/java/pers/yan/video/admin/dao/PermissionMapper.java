package pers.yan.video.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.yan.video.admin.pojo.entity.Permission;

/**
 * 权限mapper
 * @author likaiyan
 * @date 2020/8/27 6:05 下午
 */
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
}
