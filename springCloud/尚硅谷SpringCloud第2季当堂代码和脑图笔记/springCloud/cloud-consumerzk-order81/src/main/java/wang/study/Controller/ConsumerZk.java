package wang.study.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import wang.study.myLB.IMyLoadBalace;

import java.util.List;

/**
 * Author:   wang
 * Date:     2022/5/17 20:35
 * function: 测试 zk 消费者
 */
@RestController
public class ConsumerZk {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private IMyLoadBalace myLoadBalace;

    @Autowired
    private DiscoveryClient discoveryClient;



    private String URL = "http://cloud-provider-payment";

    @RequestMapping("/consumerzk/getInfo")
    public String getProvide() {
        String forObject = restTemplate.getForObject(URL + "/payment/zk", String.class);
        System.out.println("生产者返回：" + forObject);
        return forObject;
    }


    /**
     * 测试自己的 LB
     * @return
     */
    @GetMapping(value = "/consumer/myLB")
    public String testMyLB() {
        List<ServiceInstance> instances =
                discoveryClient.getInstances("cloud-provider-payment");
        ServiceInstance serviceInstance = myLoadBalace.getServiceInstance(instances);
        if (ObjectUtils.isEmpty(serviceInstance)) {
            return "未找到合适的服务提供方";
        }
        return restTemplate.getForObject(serviceInstance.getUri() + "/payment/myLB", String.class);
    }
}
