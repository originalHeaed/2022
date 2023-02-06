package org.example;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class Main {
    public static void main(String[] args) throws MQClientException {
        /* 1. 获取指定 group 的 consumer */
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("wangGroup");
        /* 2. 对 consumer 添加配置 */
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET); // 指定从哪里开始消费
        defaultMQPushConsumer.setNamesrvAddr("112.74.97.91:9876"); // 指定 nameserver 地址和端口
        /* 3. 订阅需要的 topic 和 tag */
        defaultMQPushConsumer.subscribe("20230202Topic", "tag1");
        /* 4. 订阅处理消息的回调函数 */
        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                /* 处理本批次返回的消息 */
                for (MessageExt messageExt : list) {
                    System.out.println(messageExt);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        /* 5. 开启消费 */
        defaultMQPushConsumer.start();
        System.out.println("MQ Consumer 启动完成");
    }
}