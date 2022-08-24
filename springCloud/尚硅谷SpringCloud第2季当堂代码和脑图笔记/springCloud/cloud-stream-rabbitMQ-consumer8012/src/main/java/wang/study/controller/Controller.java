package wang.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:   wang
 * Date:     2022/6/9 22:22
 * function:
 */
@RestController
public class Controller {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/zipkin")
    public String testZipkin() {
        return "I am iron man" + serverPort;
    }
}
