package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/5/17 22:12
 * function: consul 生产者启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentConsulMain80006 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentConsulMain80006.class, args);
    }
}
