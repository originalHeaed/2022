package org.example.filterMsg;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;

/**
 * 标签消息发送器
 */
public class TagsSender {
    /**
     * namesrv 地址和端口
     */
    public static String NAME_ADDR = "112.74.97.91:9876";

    /**
     * topic
     */
    public static String TOPIC = "filterTP";

    /**
     * group
     */
    public static String GROUP = "filterGP";

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        /* 1. 启动生产者 */
        DefaultMQProducer producer = new DefaultMQProducer(GROUP);
        /* 2. 进行配置 */
        producer.setNamesrvAddr(NAME_ADDR);
        producer.start();
        /* 3. 开始发送不同 tag 的消息 */
        for (int i = 0; i < 50; i++) {
            Message m = new Message(TOPIC, (i % 2) == 0 ? "TAGA" : "TAGB", "body".getBytes(Charset.forName("utf-8")));
            SendResult send = producer.send(m);
            System.out.println(send);
        }
        /* 4. 关闭 producer */
        producer.shutdown();
    }
}
