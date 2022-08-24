package wang.study.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestTemplate;

/*********************************************************
 * 文件名称：TestController.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.controller
 * 功能说明：
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/21 15:41
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
@RestController
public class TestController {
    /**
     * 服务调用负载均衡 client
     */
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /**
     * 发送 restFul 请求
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 调用生产者
     * @param id
     * @return
     */
    @RequestMapping("/consumerTest/{id}")
    // @SentinelResource(value = "consumerTest", fallback = "handlerFallBack") //只配置 fallBack（blockException 也会走进该方法中）
    // @SentinelResource(value = "consumerTest", blockHandler = "handlerBlockException") //只配置 blockException（只有 blockException 会走进该方法）
    // @SentinelResource(value = "consumerTest", blockHandler = "handlerBlockException", fallback = "handlerFallBack") //  fallBack 和 blockException 均配置，先校验 blockHandler 再校验 fallBack
    @SentinelResource(value = "consumerTest", blockHandler = "handlerBlockException", fallback = "handlerFallBack", exceptionsToIgnore = ArithmeticException.class) //  配置 exceptionsToIgnore
    public String consumerTest(@PathVariable(value = "id") String id) {
        if ("4".equals(id)) {
            int i = 10 /0; // 抛出非 blockerException
        }
        //Access through the combination of LoadBalanceClient and RestTemplate
        ServiceInstance serviceInstance = loadBalancerClient.choose("cloudAlibaba-sentinel-provide");
        String path = String.format("http://%s:%s/%s",serviceInstance.getHost(), serviceInstance.getPort(), "/sentinelTest/" + id);
        System.out.println("request path:" +path);
        return restTemplate.getForObject(path,String.class);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public String handlerFallBack(String id, Throwable throwable) {
        return "程序运行时出现了自己的异常，ps：哦豁，你出错了哟";
    }

    public String handlerBlockException(String id, BlockException blockException) {
        return "进行服务治理时出错";
    }
}
