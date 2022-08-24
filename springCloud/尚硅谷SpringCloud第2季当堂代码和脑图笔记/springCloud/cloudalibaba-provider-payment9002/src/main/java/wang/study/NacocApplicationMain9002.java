package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Author:   wang
 * Date:     2022/7/2 15:12
 * function: 主启启动类
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacocApplicationMain9002 {
    public static void main(String[] args) {
        SpringApplication.run(NacocApplicationMain9002.class, args);
    }
}
