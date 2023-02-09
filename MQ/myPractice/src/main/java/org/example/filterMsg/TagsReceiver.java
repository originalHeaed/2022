package org.example.filterMsg;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.checkerframework.checker.units.qual.C;

import java.util.List;

/**
 * tags 消息过滤消费者（只有 push 方式可以使用 tags 进行过滤）
 */
public class TagsReceiver {
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

    public static void main(String[] args) throws MQClientException {
        /* 1. 定义消费者 */
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(GROUP);
        /* 2. 进行配置 */
        defaultMQPushConsumer.setNamesrvAddr(NAME_ADDR);
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET); // 定义消费起点
        defaultMQPushConsumer.subscribe(TOPIC, "TAGA"); // 订阅指定 topic 下指定 tag 的消息
        defaultMQPushConsumer.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) -> {
            System.out.println("开始消费，本次消费条数：" + list.size());
            for (MessageExt messageExt : list) System.out.println(messageExt.getTags());
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        /* 启动消费者 */
        defaultMQPushConsumer.start();
    }
}
