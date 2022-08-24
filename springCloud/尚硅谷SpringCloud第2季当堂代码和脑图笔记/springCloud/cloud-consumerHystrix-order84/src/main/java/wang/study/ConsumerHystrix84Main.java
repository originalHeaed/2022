package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Author:   wang
 * Date:     2022/5/28 19:53
 * function: hystrix 消费端启动类
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ConsumerHystrix84Main {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerHystrix84Main.class, args);
    }
}
