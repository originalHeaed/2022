package org.example.testOrderMsg;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 分区消息顺序
 * 实际上就是另一种：全局消息顺序
 * 在全局消息顺序中，我们只有一个 queue，因此在投递消息时不用选择 queue，在分区顺序中，我们在投递消息前就按照指定的规则确定该类型
 * 消息投递到指定的队列中，在消费端使用顺序读取即可
 */
public class ShardingPartSendAndRec extends AbstractSendAndRecervice{
    @Override
    public void send() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(this.addr);
        producer.setDefaultTopicQueueNums(2); // 设置 queue 的总数
        producer.start();
        String id = "10";
        for (int i = 0; i < 100; i++) {
            Message msg = new Message(this.topic, ("test:" + i).getBytes(Charset.forName("utf-8")));
            msg.setKeys(String.valueOf(i));
             SendResult send = producer.send(msg, (List<MessageQueue> list, Message message, Object o) -> {
                 int oId = Integer.valueOf((String) o);
                 /* 获取需要发送到那个 queue 中 */
                 return list.get(oId % 2);
                 }, id);
            System.out.println(send);
        }
        producer.shutdown();
    }

    @Override
    public void reciver() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(this.groupName);
        defaultMQPushConsumer.setNamesrvAddr(this.addr);
        defaultMQPushConsumer.subscribe(this.topic, "*");
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.registerMessageListener((List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) -> {
            for (MessageExt messageExt : list) {
                System.out.println(messageExt);
            }
            return ConsumeOrderlyStatus.SUCCESS;
        });
        defaultMQPushConsumer.start();
    }

    public static void main(String[] args) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        ShardingPartSendAndRec shardingPartSendAndRec = new ShardingPartSendAndRec();
        shardingPartSendAndRec.addr = "112.74.97.91:9876";
        shardingPartSendAndRec.topic = "shardingPartTopic2";
        shardingPartSendAndRec.groupName = "shardingPartGp";
        shardingPartSendAndRec.test();
    }
}
