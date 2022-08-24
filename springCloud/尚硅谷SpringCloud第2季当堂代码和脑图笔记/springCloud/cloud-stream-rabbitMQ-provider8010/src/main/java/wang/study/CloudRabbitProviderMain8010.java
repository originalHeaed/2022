package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/6/6 20:58
 * function: 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudRabbitProviderMain8010 {
    public static void main(String[] args) {
        SpringApplication.run(CloudRabbitProviderMain8010.class, args);
    }
}
