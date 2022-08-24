package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/5/17 20:32
 * function: 消费者 zk 81端口
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderMain81 {
    public static void main(String[] args) {
        SpringApplication.run(OrderMain81.class, args);
    }
}
