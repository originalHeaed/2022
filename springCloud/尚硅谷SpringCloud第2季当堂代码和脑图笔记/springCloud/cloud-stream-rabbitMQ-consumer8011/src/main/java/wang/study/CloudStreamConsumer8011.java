package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/6/6 21:55
 * function: 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CloudStreamConsumer8011 {
    public static void main(String[] args) {
        SpringApplication.run(CloudStreamConsumer8011.class, args);
    }
}
