package wang.study.config;

import org.springframework.stereotype.Component;
import wang.study.service.MyService;

/*********************************************************
 * 文件名称：FeignFallBack.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.config
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/21 16:53
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
@Component
public class FeignFallBack implements MyService {
    /**
     * feign 的 fallback
     * @param id
     * @return
     */
    @Override
    public String sentinelTest(String id) {
        return "你进入了 feign 的 fallback";
    }
}
