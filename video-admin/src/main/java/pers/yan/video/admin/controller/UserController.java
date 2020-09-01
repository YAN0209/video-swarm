package pers.yan.video.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.yan.video.admin.common.ResponseResult;
import pers.yan.video.admin.pojo.dto.AddUserParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateUserParam;
import pers.yan.video.admin.pojo.entity.User;
import pers.yan.video.admin.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户controller
 *
 * @author likaiyan
 * @date 2020/6/26 9:02 下午
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 添加用户
     *
     * @param addUserParam 参数
     * @param result       校验结果
     * @return 用户id
     */
    @PostMapping("/add")
    public ResponseResult<Integer> addUser(@Valid @RequestBody AddUserParam addUserParam, BindingResult result) {
        return ResponseResult.success(userService.addUser(addUserParam));
    }

    /**
     * 删除用户
     *
     * @param userId 用户id
     * @return /
     */
    @PostMapping("/delete")
    public ResponseResult deleteUser(@RequestParam Integer userId) {
        userService.removeById(userId);
        return ResponseResult.success(null);
    }

    /**
     * 更新用户
     *
     * @param updateUserParam 参数
     * @param result          校验结果
     * @return /
     */
    @PostMapping("/update")
    public ResponseResult updateUser(@Valid @RequestBody UpdateUserParam updateUserParam, BindingResult result) {
        userService.updateUser(updateUserParam);
        return ResponseResult.success(null);
    }

    /**
     * 所有用户
     *
     * @return 所有用户
     */
    @GetMapping("/list/all")
    public ResponseResult<List<User>> getAllUser() {
        return ResponseResult.success(userService.list());
    }

    /**
     * 搜索用户
     *
     * @param keyword 关键字
     * @param pageNum 页码
     * @param pageSize 条数
     * @return 用户列表
     */
    @GetMapping("/list/search")
    public ResponseResult<PageDto<User>> searchUser(@RequestParam(required = false) String keyword,
                                              @RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "30") Integer pageSize) {
        return ResponseResult.success(userService.searchUser(keyword, pageNum, pageSize));
    }

    /**
     * 单个用户
     *
     * @param userId 用户id
     * @return 用户
     */
    @GetMapping("/{userId}")
    public ResponseResult<User> getUser(@PathVariable Integer userId) {
        return ResponseResult.success(userService.getById(userId));
    }

}
