package wang.study.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * Author:   wang
 * Date:     2022/5/28 20:06
 * function:
 */
@RestController
public class HystrixController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping("/normalMethod/hystrix/{id}")
    public String normalMethod(@PathVariable("id")Integer id) {
        return serverPort + Thread.currentThread().getName() + "normalMethod";
    }

    /**
     * 超时方法
     * @param id
     * @return
     */
    @RequestMapping("/timeOutMethod/hystrix/{id}")
    @HystrixCommand(fallbackMethod = "timeOutMethodHandler",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="2000")
    })
    public String timeOutMethod(@PathVariable("id")Integer id) {
        if (id == 13) {
            int i = 10/0;
        }
        int sec = 1;
        try {
            TimeUnit.SECONDS.sleep(sec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverPort + Thread.currentThread().getName() + "timeOutMethod";
    }

    /**
     * timeOutMethod 托底
     */
    private String timeOutMethodHandler(Integer id) {
        return serverPort + Thread.currentThread().getName() + "不能起, sorry";
    }

}
