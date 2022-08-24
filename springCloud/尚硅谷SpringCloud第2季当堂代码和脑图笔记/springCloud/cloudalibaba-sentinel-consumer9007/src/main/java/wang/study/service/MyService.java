package wang.study.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import wang.study.config.FeignFallBack;

/*********************************************************
 * 文件名称：MyService.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.service
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/21 16:51
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
@Component
@FeignClient(value = "cloudAlibaba-sentinel-provide", fallback = FeignFallBack.class) // 配置需要调用的服务名
public interface MyService {
    /* 调用远程服务 */
    @RequestMapping("/sentinelTest/{id}")
    public String sentinelTest(@PathVariable(value = "id") String id);
}
