package pers.yan.video.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 管理员应用
 *
 * @author likaiyan
 * @date 2020/8/27 9:53 上午
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("pers.yan.video")
public class AdminApplication extends SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
