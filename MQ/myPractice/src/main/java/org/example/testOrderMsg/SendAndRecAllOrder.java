package org.example.testOrderMsg;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 测试全局有序
 * 生产者实现消息顺序存储：只有一个线程串行先 mq 中存储消息
 * mq 保证消息顺序：只有一个 queue（因此只要发送过来的消息有顺序，则存储在 queue 中的消息一定有顺序）
 * 消费者保证消费有顺序：使用顺序模式进行消费（因此只需要保证消费者消费的 queue 中消息是顺序的，则消费过程也一定是有顺序的）
 * 需要考虑消息重复消费的问题
 */
public class SendAndRecAllOrder extends AbstractSendAndRecervice {

    @Override
    public void send() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(this.addr); // 设置 nameserver 地址
        producer.setDefaultTopicQueueNums(1); // 将 queue 的数量设定为 1，实现全局消息的有序性
        producer.start();
        /* 发送消息 */
        for (int i = 0; i < 100; i++) {
            Message message = new Message(this.topic, ("allOrder:" + i).getBytes(Charset.forName("utf-8")));
            message.setKeys(String.valueOf(i));
            SendResult send = producer.send(message); // 同步生产
            System.out.println(send);
        }
        /* 关闭消息生产者队列 */
        producer.shutdown();
    }

    @Override
    public void reciver() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(this.groupName); // 无消费者组的消费则（可以消费所有消息）
        defaultMQPushConsumer.setNamesrvAddr(this.addr);
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.subscribe(this.topic, "*"); // 订阅 topic
        // 定义回调函数
        defaultMQPushConsumer.registerMessageListener((List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) -> {
            for (MessageExt messageExt : list) {
                System.out.println(messageExt);
            }
            return ConsumeOrderlyStatus.SUCCESS;
        });
        /* 启动消费者 */
        defaultMQPushConsumer.start();
    }

    public static void main(String[] args) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        SendAndRecAllOrder sendAndRecAllOrder = new SendAndRecAllOrder();
        sendAndRecAllOrder.addr = "112.74.97.91:9876";
        sendAndRecAllOrder.topic = "allOrderMsg2";
        sendAndRecAllOrder.groupName = "allOrder";
        sendAndRecAllOrder.test();
    }
}
