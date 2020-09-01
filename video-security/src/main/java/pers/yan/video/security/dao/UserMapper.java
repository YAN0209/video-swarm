package pers.yan.video.security.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import pers.yan.video.security.pojo.entity.User;

/**
 * 用户mapper
 *
 * @author likaiyan
 * @date 2020/8/28 6:25 下午
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}