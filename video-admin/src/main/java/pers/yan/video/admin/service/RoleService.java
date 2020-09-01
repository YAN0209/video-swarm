package pers.yan.video.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import pers.yan.video.admin.pojo.dto.AddRoleParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateRoleParam;
import pers.yan.video.admin.pojo.dto.UserRoleRelateDto;
import pers.yan.video.admin.pojo.entity.Role;

/**
 * 角色service
 *
 * @author likaiyan
 * @date 2020/8/27 5:33 下午
 */
public interface RoleService extends IService<Role> {

    /**
     * 添加角色
     * @param addRoleParam 参数
     * @return 角色id
     */
    int addRole(AddRoleParam addRoleParam);

    /**
     * 更新角色
     * @param updateRoleParam 参数
     */
    void updateRole(UpdateRoleParam updateRoleParam);

    /**
     * 搜索角色
     * @param keyword 关键字
     * @param pageNum 页码
     * @param pageSize 条数
     * @return 分页实体
     */
    PageDto<Role> searchRole(String keyword, int pageNum, int pageSize);

    /**
     * 添加用户角色
     * @param userRoleRelateDto 参数
     */
    void addUserRole(UserRoleRelateDto userRoleRelateDto);

}