package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Author:   wang
 * Date:     2022/5/24 21:38
 * function: 测试 openFeign 启动类
 */
@SpringBootApplication
@EnableFeignClients
public class ConsumerOpenFeignMain83 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOpenFeignMain83.class, args);
    }
}
