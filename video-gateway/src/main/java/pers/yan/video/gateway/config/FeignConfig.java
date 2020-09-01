package pers.yan.video.gateway.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign配置
 * @author likaiyan
 * @date 2020/9/1 2:17 下午
 */
@Configuration
public class FeignConfig {

    @Bean
    public HttpMessageConverters httpMessageConverters(){
        return new HttpMessageConverters();
    }

}
