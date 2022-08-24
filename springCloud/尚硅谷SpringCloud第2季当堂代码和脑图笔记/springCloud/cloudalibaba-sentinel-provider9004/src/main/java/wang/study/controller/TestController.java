package wang.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*********************************************************
 * 文件名称：TestController.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：main.java.controller
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/21 15:00
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
@RestController
public class TestController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/sentinelTest/{id}")
    public String sentinelTest(@PathVariable(value = "id") String id) {
        if (id == "4") return null;
        return port;
    }
}
