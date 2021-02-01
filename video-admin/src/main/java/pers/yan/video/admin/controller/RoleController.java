package pers.yan.video.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pers.yan.video.admin.pojo.dto.AddRoleParam;
import pers.yan.video.admin.pojo.dto.PageDto;
import pers.yan.video.admin.pojo.dto.UpdateRoleParam;
import pers.yan.video.admin.pojo.entity.Role;
import pers.yan.video.admin.service.RoleService;
import pers.yan.video.common.common.ResponseResult;

import javax.validation.Valid;
import java.util.List;


/**
 * 角色controller
 *
 * @author likaiyan
 * @date 2020/8/27 5:20 下午
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public ResponseResult<Integer> addRole(@Valid @RequestBody AddRoleParam addRoleParam, BindingResult result) {
        return ResponseResult.success(roleService.addRole(addRoleParam));
    }

    @PostMapping("/delete")
    public ResponseResult deleteRole(@RequestParam Integer roleId) {
        roleService.removeById(roleId);
        return ResponseResult.success(null);
    }

    @PostMapping("/update")
    public ResponseResult updateRole(@Valid @RequestBody UpdateRoleParam updateRoleParam, BindingResult result) {
        roleService.updateRole(updateRoleParam);
        return ResponseResult.success(null);
    }

    @GetMapping("/list/all")
    public ResponseResult<List<Role>> getAllRole() {
        return ResponseResult.success(roleService.list());
    }

    @GetMapping("/list/search")
    public ResponseResult<PageDto<Role>> searchRole(@RequestParam(required = false) String keyword,
                                              @RequestParam(defaultValue = "1") Integer pageNum,
                                              @RequestParam(defaultValue = "30") Integer pageSize) {
        return ResponseResult.success(roleService.searchRole(keyword, pageNum, pageSize));
    }

    @GetMapping("/{roleId}")
    public ResponseResult<Role> getRole(@PathVariable Integer roleId) {
        return ResponseResult.success(roleService.getById(roleId));
    }

}
