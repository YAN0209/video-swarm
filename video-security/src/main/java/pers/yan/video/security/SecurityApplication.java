package pers.yan.video.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author likaiyan
 * @date 2020/9/1 11:37 上午
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("pers.yan.video")
public class SecurityApplication extends SpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }
}
