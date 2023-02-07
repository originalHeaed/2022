package org.example.testDelayMsg;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.checkerframework.checker.units.qual.C;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 延迟消息接受方
 */
public class MyDelayMsgReciver {
    public static final String GP = "delayMsgGp";

    public static final String ADDR = "112.74.97.91:9876";

    public static final String TOPIC = "delayMsg";

    public static void main(String[] args) throws MQClientException {
        /* 创建 consumer */
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(GP);
        /* 进行配置 */
        defaultMQPushConsumer.setNamesrvAddr(ADDR);
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET); // 这个很重要
        defaultMQPushConsumer.subscribe(TOPIC, "*"); // 订阅 topic
        defaultMQPushConsumer.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) -> {
            for (MessageExt messageExt : list)
                System.out.println(new SimpleDateFormat("mm:ss").format(new Date()) + messageExt);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        /* 启动消费者 */
        defaultMQPushConsumer.start();
    }
}
