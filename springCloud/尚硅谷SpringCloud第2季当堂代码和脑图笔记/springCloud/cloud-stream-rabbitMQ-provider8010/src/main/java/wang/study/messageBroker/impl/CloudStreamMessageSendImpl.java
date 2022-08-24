package wang.study.messageBroker.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import wang.study.messageBroker.ICloudStreamMessageSend;

import javax.annotation.Resource;

/**
 * Author:   wang
 * Date:     2022/6/6 21:00
 * function: 消息生产者实现类
 */
@EnableBinding(Source.class)
public class CloudStreamMessageSendImpl implements ICloudStreamMessageSend {

    /**
     * 消息通道
     */
    @Autowired
    @Qualifier("output")
    private MessageChannel ouput;

    @Override
    public void sendMsg() {
        String s = "I am king of king of king";
        ouput.send(MessageBuilder.withPayload(s).build());
        System.out.println("he said: " + s);
    }
}
