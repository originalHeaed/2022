package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/7/2 20:55
 * function: 启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigMain9015 {
    public static void main(String[] args) {
        SpringApplication.run(NacosConfigMain9015.class, args);
    }
}
