package pers.yan.video.gateway.service.fallback;

import pers.yan.video.common.common.ResponseCode;
import pers.yan.video.common.common.ResponseResult;
import pers.yan.video.gateway.pojo.entity.Permission;
import pers.yan.video.gateway.pojo.entity.Role;
import pers.yan.video.gateway.service.AuthRemote;

import java.util.List;

/**
 * AuthRemote 熔断处理
 * @author likaiyan
 * @date 2020/9/1 9:53 上午
 */
public class AuthRemoteFallBack implements AuthRemote {

    @Override
    public ResponseResult<List<Role>> getRoles(int userId) {
        return ResponseResult.exception(ResponseCode.FAILED, "exception.ServiceNoResponse");
    }

    @Override
    public ResponseResult<List<Permission>> getPermissionsByUser(int userId) {
        return ResponseResult.exception(ResponseCode.FAILED, "exception.ServiceNoResponse");
    }

    @Override
    public ResponseResult<List<Permission>> getPermissionsByRole(int roleId) {
        return ResponseResult.exception(ResponseCode.FAILED, "exception.ServiceNoResponse");
    }
}
