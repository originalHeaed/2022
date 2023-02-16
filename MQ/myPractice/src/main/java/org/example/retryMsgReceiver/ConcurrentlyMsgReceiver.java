package org.example.retryMsgReceiver;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 无序消息消费失败重试
 */
public class ConcurrentlyMsgReceiver {
    /**
     * namesrv 的 ip:port
     */
    public static String NAME_SRV = "112.74.97.91:9876";

    /**
     * 消息 topic
     */
    public static String TOPIC = "retryConcurrentlyTp";

    /**
     * 消费者 group
     */
    public static String GP = "retryConcurrentlyGP";

    public static void main(String[] args) throws MQClientException {
        /* 1. 定义消费者 */
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(GP);
        /* 2. 进行配置 */
        defaultMQPushConsumer.setNamesrvAddr(NAME_SRV);
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        /* todo 对于无序消息，不能直接直接消费超时时间，其消息消费超时时间有自己的规则，可以指定其最大消费重试次数（默认 16）  */
        defaultMQPushConsumer.setMaxReconsumeTimes(1);
        defaultMQPushConsumer.subscribe(TOPIC, "*");
        defaultMQPushConsumer.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) -> {
            /* 如果结果返回 ConsumeConcurrentlyStatus.RECONSUME_LATER、null、抛出异常 该消息后续将会进行
             * 失败重试，需要进行重试的失败消息将会放在延迟队列中，等待指定时间间隔过后，在放回正常队列中进行消费 */
            return ConsumeConcurrentlyStatus.RECONSUME_LATER;
        });
        /* 3. 启动消费者 */
        defaultMQPushConsumer.start();
    }

}
