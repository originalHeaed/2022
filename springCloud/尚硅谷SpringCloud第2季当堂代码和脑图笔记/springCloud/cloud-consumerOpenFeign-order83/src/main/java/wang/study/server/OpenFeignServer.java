package wang.study.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wgh.study.entities.CommonResult;
import wgh.study.entities.Payment;

/**
 * Author:   wang
 * Date:     2022/5/24 21:40
 * function: openFeignServer
 */
@Component
@FeignClient(value = "cloud-payment-service")
public interface OpenFeignServer {

    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);
}
