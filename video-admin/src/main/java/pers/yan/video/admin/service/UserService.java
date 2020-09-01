package pers.yan.video.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.yan.video.admin.pojo.dto.AddUserParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateUserParam;
import pers.yan.video.admin.pojo.entity.User;

/**
 * 用户管理
 *
 * @author likaiyan
 * @date 2020/6/8 5:24 下午
 */
public interface UserService extends IService<User> {

    /**
     * 添加用户
     *
     * @param addUserParam 参数
     * @return 用户id
     */
    int addUser(AddUserParam addUserParam);


    /**
     * 更新用户
     *
     * @param updateUserParam 参数
     */
    void updateUser(UpdateUserParam updateUserParam);

    /**
     * 查询用户
     *
     * @param keyword  关键字
     * @param pageNum  页码
     * @param pageSize 条数
     * @return 分页对象
     */
    PageDto<User> searchUser(String keyword, int pageNum, int pageSize);

}