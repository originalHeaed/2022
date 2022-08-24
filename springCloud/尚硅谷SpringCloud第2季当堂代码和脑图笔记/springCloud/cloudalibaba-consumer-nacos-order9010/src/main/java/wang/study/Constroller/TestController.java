package wang.study.Constroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Author:   wang
 * Date:     2022/7/2 15:35
 * function:
 */
@RestController
public class TestController {


    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.application.name}")
    private String appName;

    @Value("${provider.name}")
    private String providerName;

    @Value("${provider.uri}")
    private String providerURI;

    @GetMapping("/nacos/consumer")
    public String echoAppName(){
        //Access through the combination of LoadBalanceClient and RestTemplate
        ServiceInstance serviceInstance = loadBalancerClient.choose(providerName);
        String path = String.format("http://%s:%s/%s",serviceInstance.getHost(), serviceInstance.getPort(), providerURI);
        System.out.println("request path:" +path);
        return restTemplate.getForObject(path,String.class);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
