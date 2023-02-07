package org.example.testDelayMsg;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.checkerframework.checker.units.qual.A;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 延迟消息发送方
 */
public class MyDelayMsgSender {
    public static final String GP = "delayMsgGp";

    public static final String ADDR = "112.74.97.91:9876";

    public static final String TOPIC = "delayMsg";

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        /* 定义 producer */
        DefaultMQProducer producer = new DefaultMQProducer(GP);
        /* 进行配置 */
        producer.setNamesrvAddr(ADDR);
        producer.setDefaultTopicQueueNums(2); // 默认两个队列
        producer.start();
        /* 发送消息 */
        Message message = new Message(TOPIC, "delayMsg".getBytes(Charset.forName("utf-8")));
        message.setDelayTimeLevel(3); // 设置延迟等级
        SendResult send = producer.send(message);
        System.out.println(new SimpleDateFormat("mm:ss").format(new Date()) + send);
        /* 关闭生产者 */
        producer.shutdown();
    }
}
