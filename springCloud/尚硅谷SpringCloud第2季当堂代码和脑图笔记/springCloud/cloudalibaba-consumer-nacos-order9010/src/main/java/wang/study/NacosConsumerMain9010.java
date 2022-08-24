package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/7/2 15:34
 * function: 主启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConsumerMain9010 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerMain9010.class, args);
    }
}
