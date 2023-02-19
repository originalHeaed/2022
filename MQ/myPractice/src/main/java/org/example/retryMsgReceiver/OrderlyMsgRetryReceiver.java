package org.example.retryMsgReceiver;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQPullConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 顺序消息重复消费
 */
public class OrderlyMsgRetryReceiver {

    /**
     * namesrv 的 ip:port
     */
    public static String NAME_SRV = "112.74.97.91:9876";

    /**
     * 消息 topic
     */
    public static String TOPIC = "retryOrderlyTp2";

    /**
     * 消费者 group
     */
    public static String GP = "retryOrderlyGP";

    public static void main(String[] args) throws MQClientException {
        /* 1. 创建消费者 */
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(GP);
        /* 2. 进行配置 */
        defaultMQPushConsumer.setNamesrvAddr(NAME_SRV); // namesrv ip:port
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET); // 从哪里开始消费
        defaultMQPushConsumer.subscribe(TOPIC, "*");
        // todo 设置顺序消息的消费超时时间，取值范围：[10, 30000]，默认值：1000，单位 ms
        defaultMQPushConsumer.setSuspendCurrentQueueTimeMillis(1000);
        // todo 对于顺序消息来说，该字段可以设置顺序消息消费失败重试的最大值，默认顺序消费的最大值 Integer.MAX_VALUE
        defaultMQPushConsumer.setMaxReconsumeTimes(1);
        defaultMQPushConsumer.registerMessageListener((List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) -> {
            /* 1. 使用顺序消费方式拉取消息，然后按照指定的方式进行排序，存放在 list 中 */
            /* 2. 如果结果返回 ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT、null、抛出异常 该消息后续将会进行
            * 失败重试，但是需要注意，在顺序消息消费过程中，如果某条消息消费失败，则该消费者组无法消费后续其他的消息 */
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }
            System.out.println("消费失败");
            return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
        });
        /* 3. 启动消费者 */
        defaultMQPushConsumer.start();
    }
}
