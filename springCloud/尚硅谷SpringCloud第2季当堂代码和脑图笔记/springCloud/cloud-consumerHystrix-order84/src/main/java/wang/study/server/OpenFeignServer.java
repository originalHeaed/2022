package wang.study.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Author:   wang
 * Date:     2022/5/28 19:55
 * function: 服务调用
 */
@Component
@FeignClient(value = "cloud-providerHystrix-payment")
public interface OpenFeignServer {

    @RequestMapping("/normalMethod/hystrix/{id}")
    public String normalMethod(@PathVariable("id")Integer id);

    @RequestMapping("/timeOutMethod/hystrix/{id}")
    public String timeOutMethod(@PathVariable("id")Integer id);
}
