package wang.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.study.messageBroker.ICloudStreamMessageSend;

/**
 * Author:   wang
 * Date:     2022/6/6 21:09
 * function: controller
 */
@RestController
public class CloudStreamProviderController {
    @Autowired
    private ICloudStreamMessageSend messageSend;

    @RequestMapping("/rabbitMQ/send")
    public String sendMessage() {
        messageSend.sendMsg();
        return "yes, you success!!";
    }
}
