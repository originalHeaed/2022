package org.example.testOrderMsg;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;

/**
 * 正常收发消息（根据 key 值来看，确实是乱序消费的）
 */
public class NormalSendAndRecervice extends AbstractSendAndRecervice{

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        NormalSendAndRecervice normalSendAndRecervice = new NormalSendAndRecervice();
        normalSendAndRecervice.topic = "normalMsg";
        normalSendAndRecervice.addr = "112.74.97.91:9876";
        normalSendAndRecervice.test();
    }

    @Override
    public void send() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer( "normalMsgProducerGp");
        producer.setNamesrvAddr(this.addr);
        producer.setDefaultTopicQueueNums(2); // 设置队列总数
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message message = new Message(this.topic, "test", ("hello " + i).getBytes(Charset.forName("utf-8")));
            message.setKeys(String.valueOf(i));
            SendResult send = producer.send(message); //同步发送消息
            System.out.println(send);
        }
        producer.shutdown(); // 发送完成，关闭生产者线程
    }

    @Override
    public void reciver() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("normalMsgConsumerGp");
        consumer.setNamesrvAddr(this.addr);
        consumer.subscribe(this.topic, "*"); // 消费者订阅指定 topic
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET); // 哪里开始消费
        consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
            for (MessageExt messageExt : list) {
                System.out.println(messageExt);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start(); // 配置完成后，启动消费者
    }
}
