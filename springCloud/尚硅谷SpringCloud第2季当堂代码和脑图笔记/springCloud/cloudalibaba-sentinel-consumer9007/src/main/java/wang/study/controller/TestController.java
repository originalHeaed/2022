package wang.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.study.service.MyService;

/*********************************************************
 * 文件名称：TestController.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.controller
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/21 16:50
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
@RestController
public class TestController {

    /**
     * 进行远程调用
     */
    @Autowired
    private MyService myService;

    @RequestMapping("/testFeign/{id}")
    public String testFeign(@PathVariable(value = "id") String id) {
        return myService.sentinelTest(id);
    }
}
