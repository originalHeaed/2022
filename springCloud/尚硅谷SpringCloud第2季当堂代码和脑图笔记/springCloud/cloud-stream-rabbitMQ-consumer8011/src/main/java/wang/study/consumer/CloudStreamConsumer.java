package wang.study.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;

/**
 * Author:   wang
 * Date:     2022/6/6 21:55
 * function: 消费者
 */
@EnableBinding(Sink.class)
public class CloudStreamConsumer {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(Sink.INPUT)
    public void receiveMse(Message<String> message) {
        System.out.println("消费者收到：" + message.getPayload() + " port:" + serverPort);
    }

}
