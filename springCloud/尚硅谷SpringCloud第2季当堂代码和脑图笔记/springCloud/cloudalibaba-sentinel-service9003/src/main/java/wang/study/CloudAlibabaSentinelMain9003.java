package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/7/10 22:05
 * function: 启动类
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CloudAlibabaSentinelMain9003 {
    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaSentinelMain9003.class, args);
    }
}
