package pers.yan.video.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.yan.video.admin.pojo.entity.UserRoleRelation;

/**
 * 用户角色关联类mapper
 * @author likaiyan
 * @date 2020/8/31 11:50 上午
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRoleRelation> {
}
