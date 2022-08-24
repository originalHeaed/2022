package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/5/28 20:04
 * function: 启动类
 */
@SpringCloudApplication
public class ProviderHystrixMain8009 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderHystrixMain8009.class, args);
    }
}
