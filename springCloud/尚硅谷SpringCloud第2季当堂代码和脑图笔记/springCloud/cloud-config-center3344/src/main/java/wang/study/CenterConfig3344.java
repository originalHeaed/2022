package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Author:   wang
 * Date:     2022/6/1 22:42
 * function: 启动类
 */
@SpringBootApplication
@EnableConfigServer
public class CenterConfig3344 {
    public static void main(String[] args) {
        SpringApplication.run(CenterConfig3344.class, args);
    }
}
