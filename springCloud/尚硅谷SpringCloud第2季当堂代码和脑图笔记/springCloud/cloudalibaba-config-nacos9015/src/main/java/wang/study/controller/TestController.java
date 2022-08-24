package wang.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:   wang
 * Date:     2022/7/2 20:56
 * function:
 */
@RestController
@RefreshScope
public class TestController {

    @Value("${useLocalCache:false}")
    private String useLocalCache;

    @RequestMapping("/config/get")
    public String get() {
        return "useLocalCache：" + useLocalCache;
    }
}
