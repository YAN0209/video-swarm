package pers.yan.video.admin.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.yan.video.admin.pojo.entity.User;

/**
 * 用户mapper
 *
 * @author likaiyan
 * @date 2020/8/27 11:18 上午
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}