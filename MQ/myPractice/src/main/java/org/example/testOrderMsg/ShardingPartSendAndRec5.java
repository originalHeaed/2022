package org.example.testOrderMsg;

import io.netty.handler.codec.mqtt.MqttMessageBuilders;
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

public class ShardingPartSendAndRec5 extends AbstractSendAndRecervice{
    @Override
    public void send() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
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
