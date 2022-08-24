package wang.study.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * Author:   wang
 * Date:     2022/5/17 20:35
 * function: 测试 zk 消费者
 */
@RestController
public class ConsumerConsul {

    @Resource
    private RestTemplate restTemplate;

    private String URL = "http://consul-provider-payment";

    @GetMapping("/consumerConsul/getInfo")
    public String getProvide() {
        System.out.println("dddddd");
        String forObject = restTemplate.getForObject(URL + "/payment/consul", String.class);
        System.out.println("生产者返回：" + forObject);
        return forObject;
    }
}
