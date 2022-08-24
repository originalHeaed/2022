package wang.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.study.server.OpenFeignServer;
import wgh.study.entities.CommonResult;
import wgh.study.entities.Payment;

/**
 * Author:   wang
 * Date:     2022/5/24 21:43
 * function: openFeign controller
 */
@RestController
public class OpenFeignController {

    @Autowired
    private OpenFeignServer openFeignServer;

    @RequestMapping("/testOpenFeign")
    public CommonResult<Payment> testOpenFeign() {
        return openFeignServer.getPaymentById(13L);
    }
}
