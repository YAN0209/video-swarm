package pers.yan.video.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.yan.video.admin.dao.UserMapper;
import pers.yan.video.admin.pojo.dto.AddUserParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateUserParam;
import pers.yan.video.admin.pojo.entity.User;
import pers.yan.video.admin.service.UserService;

/**
 * 用户service
 *
 * @author likaiyan
 * @date 2020/8/27 3:11 下午
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public int addUser(AddUserParam addUserParam) {
        User user = new User();
        BeanUtils.copyProperties(addUserParam, user);
        this.save(user);
        return user.getId();
    }

    @Override
    public void updateUser(UpdateUserParam updateUserParam) {
        User user = new User();
        BeanUtils.copyProperties(updateUserParam, user);
        this.updateById(user);
    }

    @Override
    public PageDto<User> searchUser(String keyword, int pageNum, int pageSize) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyword)){
            wrapper.like("username", keyword);
        }
        Page<User> page = new Page<>(pageNum, pageSize);
        this.page(page, wrapper);
        PageDto<User> pageDto = new PageDto<>();
        BeanUtils.copyProperties(page, pageDto);
        return pageDto;
    }


}
