package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/5/28 20:04
 * function: 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ProviderHystrixMain8008 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderHystrixMain8008.class, args);
    }
}
