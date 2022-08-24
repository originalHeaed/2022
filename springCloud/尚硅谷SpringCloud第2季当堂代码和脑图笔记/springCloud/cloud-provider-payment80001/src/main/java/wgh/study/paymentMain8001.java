package wgh.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Author:   wang
 * Date:     2022/5/4 15:57
 * function: 启动类
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class paymentMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(paymentMain8001.class, args);
    }
}
