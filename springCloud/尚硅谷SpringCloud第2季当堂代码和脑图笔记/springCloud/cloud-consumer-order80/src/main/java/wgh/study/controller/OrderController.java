package wgh.study.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import wgh.study.entities.CommonResult;
import wgh.study.entities.Payment;

import javax.annotation.Resource;

/**
 * Author:   wang
 * Date:     2022/5/4 21:51
 * function: 订单 controller
 */
@RestController
public class OrderController {
    /**
     * 通用 url
     */
    private static final String URL = "http://cloud-payment-service";

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        return restTemplate.postForObject(URL + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public CommonResult<Payment> get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(URL + "/payment/get/" + id, CommonResult.class);
    }


    @GetMapping("/consumer/payment/getByEntity/{id}")
    public CommonResult<Payment> getByEntity(@PathVariable("id") Long id) {
        ResponseEntity<CommonResult> forEntity = restTemplate.getForEntity(URL + "/payment/get/" + id, CommonResult.class);
        if (forEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("远程 rpc 调用成功");
            return forEntity.getBody();
        } else {
            System.out.println("远程 rpc 调用失败！");
            return new CommonResult<Payment>("500", "远程 rpc 调用失败", null);
        }
    }
}
