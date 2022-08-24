package wang.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*********************************************************
 * 文件名称：wang.study.ProviderMain9004.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：main.java
 * 功能说明：启动类
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/21 15:00
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderMain9004 {
    public static void main(String[] args) {
        SpringApplication.run(ProviderMain9004.class, args);
    }
}
