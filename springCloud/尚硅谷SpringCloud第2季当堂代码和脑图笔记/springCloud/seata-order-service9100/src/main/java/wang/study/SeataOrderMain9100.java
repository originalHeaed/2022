package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/*********************************************************
 * 文件名称：SeataOrderMain9100.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study
 * 功能说明：启动类
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/25 18:00
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class SeataOrderMain9100 {
    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMain9100.class, args);
    }
}
