package pers.yan.video.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author likaiyan
 * @date 2020/9/30 4:43 下午
 */
@ComponentScan({"pers.yan.video.common.common", "pers.yan.video.search"})
@SpringBootApplication
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }
}
