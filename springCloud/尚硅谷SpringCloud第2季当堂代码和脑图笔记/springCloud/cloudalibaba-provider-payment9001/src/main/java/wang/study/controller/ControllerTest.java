package wang.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:   wang
 * Date:     2022/7/2 15:13
 * function: controller 类
 */
@RestController
public class ControllerTest {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/nacos/provider")
    public String testController() {
        return "nacos registry, serverPort: "+ port;
    }
}
