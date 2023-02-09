package org.example.batchMsg;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 批次消息接受者
 */
public class BatchMsgReceiver {


    /**
     * 发送消息的 topic
     */
    public static String TOPIC = "batchMsgTp";

    /**
     * 发送消息的 group
     */
    public static String GROUP = "batchMsgRec";

    public static void main(String[] args) throws MQClientException {
        /* 1. 创建消费者 */
        DefaultMQPushConsumer batchMsgRec = new DefaultMQPushConsumer(GROUP);
        /* 2. 进行配置 */
        batchMsgRec.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET); // 从第一条开始消费
        batchMsgRec.subscribe(TOPIC, "*"); // 订阅指定的主题
        batchMsgRec.setNamesrvAddr("112.74.97.91:9876");
        batchMsgRec.setPullBatchSize(64); // 每次最多可以拉取 64 条消息
        batchMsgRec.setConsumeMessageBatchMaxSize(10); // 每次调用回调函数最多会消费 10 条
        batchMsgRec.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) -> {
            System.out.println("本次消费总记录数：" + list.size());
            for (MessageExt messageExt : list) {
                System.out.println(messageExt);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        /* 启动消费者 */
        batchMsgRec.start();
    }
}
