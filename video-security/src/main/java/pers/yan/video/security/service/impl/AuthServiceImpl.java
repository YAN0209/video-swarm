package pers.yan.video.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.yan.video.common.utils.JwtTokenUtil;
import pers.yan.video.security.dao.UserMapper;
import pers.yan.video.security.pojo.dto.LoginParam;
import pers.yan.video.security.pojo.dto.ModifyParam;
import pers.yan.video.security.pojo.dto.RegisterParam;
import pers.yan.video.security.pojo.dto.ValidateParam;
import pers.yan.video.security.pojo.entity.User;
import pers.yan.video.security.service.AuthService;
import pers.yan.video.security.service.RedisService;
import pers.yan.video.security.util.RandomUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author likaiyan
 * @date 2020/8/31 2:22 下午
 */
@Service
public class AuthServiceImpl extends ServiceImpl<UserMapper, User> implements AuthService {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private RedisService redisService;

    @Value("${auth.expire:300}")
    private int expire;

    @Override
    public String login(LoginParam loginParam) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("loginName", loginParam.getLoginName())
                .eq("password", loginParam.getPassword());
        User user = this.getOne(wrapper);
        if (user != null) {
            return jwtTokenUtil.generateToken(user.getId());
        } else {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int register(RegisterParam registerParam) {
        User user = new User();
        BeanUtils.copyProperties(registerParam, user);
        user.setDeleteTag(false);
        user.setLockTag(true);
        user.setRegistrationTime(new Date());
        this.save(user);
        String verificationCode = RandomUtils.getRandomDigits(6);
        redisService.set(user.getEmail(), verificationCode);
        redisService.expire(user.getEmail(), expire);
        return user.getId();
    }

    @Override
    public boolean validateCode(ValidateParam validateParam) {
        String validateCode = redisService.get(validateParam.getEmail());
        return validateCode != null && validateCode.equals(validateParam.getValidateCode());
    }

    @Override
    public void modify(ModifyParam modifyParam) {
        User user = this.getById(modifyParam.getUserId());
        BeanUtils.copyProperties(modifyParam, user);
        this.updateById(user);
    }

    @Override
    public String refreshToken(HttpServletRequest request) {
        String token = jwtTokenUtil.getToken(request);
        return jwtTokenUtil.refreshToken(token);
    }
}
