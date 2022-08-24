package wang.study.controller;

import com.alibaba.csp.sentinel.adapter.spring.webflux.exception.SentinelBlockExceptionHandler;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wang.study.config.CommonBlockExceptionHandler;
import wang.study.server.ServerTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:   wang
 * Date:     2022/7/10 22:06
 * function: 测试
 */
@RestController
public class TestController {

    private int value = 0;

    @Value("${server.port}")
    private String port;

    @Autowired
    private ServerTest serverTest;

    @RequestMapping("/getPortA")
    public String getPort() {
        serverTest.test();
        return "port A: " + port;
    }

    @RequestMapping("/getPortB")
    public String getPortB() {
        serverTest.test();
        return "port B: " + port;
    }

    @RequestMapping("/getPortC")
    public String getPortC() throws InterruptedException {
        Thread.sleep(500);
        return "port C: " + port;
    }

    @RequestMapping("/getPortD")
    public String getPortD() throws InterruptedException {
        int i = 10 / value;
        return "port D: " + port;
    }

    @RequestMapping("/getPortE")
    @SentinelResource(value = "getPortE")
    public String getPortE() {
        return "port E: " + port;
    }


    /**
     * 测试根据 sentinelResource 配置流控
     * @param p1
     * @param p2
     * @return
     */
    @RequestMapping("/getPortF")
    @SentinelResource(value = "getPortF", blockHandler = "myHandler")
    public String getPortF(@RequestParam(value = "p1", required = false) String p1,
                           @RequestParam(value = "p2", required = false) String p2) {
        return "port F: " + port;
    }

    /**
     * 自定义 blockException 处理方法（注意：需要使用 public 来修饰自顶一个 blockException 异常）
     */
    public String myHandler(String p1, String p2, BlockException except) {
        return "old Exception: " + except.getMessage() + ", now you trun";
    }

    /**
     * 使用公用异常
     * @return
     */
    @RequestMapping("/getPortG")
    @SentinelResource(value = "getPortG",
            blockHandlerClass = CommonBlockExceptionHandler.class,
            blockHandler = "getF")
    public String getPortF() {
        return "port F: " + port;
    }
}
