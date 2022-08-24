package wgh.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Author:   wang
 * Date:     2022/5/16 21:52
 * function: 8004 端口服务提供方
 */
@RestController
public class Payment8004
{
    @Value("${server.port}")
    private String serverPort;

    @PostMapping(value = "/payment/zk")
    public String paymentzk()
    {
        return "springcloud with zookeeper: "+serverPort+"\t"+ UUID.randomUUID().toString();
    }

    /**
     * 测试自己的 LB
     * @return
     */
    @GetMapping(value = "/payment/myLB")
    public String testMyLB() {
        return this.serverPort;
    }
}

