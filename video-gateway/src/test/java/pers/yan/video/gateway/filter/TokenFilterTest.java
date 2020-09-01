package pers.yan.video.gateway.filter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.yan.video.common.utils.JwtTokenUtil;
import pers.yan.video.gateway.GateWayApplication;

/**
 * @author likaiyan
 * @date 2020/9/1 10:51 上午
 */
@SpringBootTest(classes = GateWayApplication.class)
@RunWith(SpringRunner.class)
public class TokenFilterTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Test
    public void tokenTest(){
        System.out.println(jwtTokenUtil.generateToken(1));
    }

}
