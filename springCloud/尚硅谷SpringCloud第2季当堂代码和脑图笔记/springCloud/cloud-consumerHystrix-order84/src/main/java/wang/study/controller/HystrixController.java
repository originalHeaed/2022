package wang.study.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import wang.study.server.OpenFeignServer;

/**
 * Author:   wang
 * Date:     2022/5/28 19:57
 * function:
 */
@RestController
@DefaultProperties(defaultFallback = "timeOutRpcHandler")
public class HystrixController {

    @Autowired
    private OpenFeignServer openFeignServer;

    @GetMapping("/normalRpc/{id}")
    public String normalRpc(@PathVariable("id") Integer id) {
        return openFeignServer.normalMethod(id);
    }

    @GetMapping("/timeOutRpc/{id}")
    @HystrixCommand
    public String timeOutRpc(@PathVariable("id") Integer id) {
        if (id == 3) {
            int i = 10/0;
        }
        return openFeignServer.timeOutMethod(id);
    }

    public String timeOutRpcHandler() {
        return "client error sorry";
    }
}
