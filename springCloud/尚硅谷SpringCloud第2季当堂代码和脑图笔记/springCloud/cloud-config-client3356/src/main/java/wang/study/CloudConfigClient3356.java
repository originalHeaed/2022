package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/6/4 17:28
 * function: 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudConfigClient3356 {
    public static void main(String[] args) {
        SpringApplication.run(CloudConfigClient3356.class, args);
    }
}
