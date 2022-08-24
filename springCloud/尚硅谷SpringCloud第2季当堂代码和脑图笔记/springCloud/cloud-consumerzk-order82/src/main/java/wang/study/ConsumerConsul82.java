package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/5/17 22:33
 * function: consul 消费者启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerConsul82 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerConsul82.class, args);
    }
}
