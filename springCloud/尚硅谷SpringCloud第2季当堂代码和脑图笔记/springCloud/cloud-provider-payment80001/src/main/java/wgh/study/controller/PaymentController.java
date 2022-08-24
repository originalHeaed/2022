package wgh.study.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import wgh.study.entities.CommonResult;
import wgh.study.entities.Payment;
import wgh.study.service.PaymentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Author:   wang
 * Date:     2022/5/4 17:11
 * function: 支付 controller 类
 */

@RestController
public class PaymentController {

    /**
     * 本服务的端口号
     */
    @Value("${server.port}")
    private String serverPort;

    /**
     * 支付 service 类
     */
    @Resource
    private PaymentService paymentService;

    /**
     * 关于服务的 discover 类
     */
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        System.out.println("入参：" + payment);
        int val = paymentService.create(payment);
        if (val > 0 ) {
            System.out.println("插入成功");
            return new CommonResult<Integer>("200", "success + " + serverPort, val);
        } else {
            System.out.println("插入失败");
            return new CommonResult<Integer>("999", "fail + " + serverPort, val);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        System.out.println("入参：" + id);
        Payment payment = paymentService.selectById(id);
        if (payment == null) {
            System.out.println("查询失败");
            return new CommonResult<Payment>("999", "查询失败 + " + serverPort, null);
        }
        System.out.println("查询成功");
        return new CommonResult<Payment>("200", "查询成功 + " + serverPort, payment);
    }


    @GetMapping("/payment/discovery")
    public Object getDiscoveryInfo() {
        List<String> services = discoveryClient.getServices();
        services.forEach(e ->{
            System.out.println("element: " + e);
            List<ServiceInstance> instances = discoveryClient.getInstances(e);
            instances.forEach(each ->{
                System.out.println("info: " + each.getHost() + ":" + each.getPort());
            });
        });
        return discoveryClient;
    }
}