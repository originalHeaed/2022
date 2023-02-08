package org.example.testTransactionMsg;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 事务消息的消费者
 */
public class TransactionMsgConsumer {
    /**
     * 消费者 groupname
     */
    public static final String TRANSACTION_MSG_GP = "transactionGp";

    /**
     * 消费者 topic
     */
    public static final String TRANSACTION_MSG_TP = "transactionTp";

    public static void main(String[] args) throws MQClientException {
        /* 1. 创建消费者 */
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(TRANSACTION_MSG_GP);
        /* 2. 进行配置 */
        defaultMQPushConsumer.setNamesrvAddr("112.74.97.91:9876");
        defaultMQPushConsumer.subscribe(TRANSACTION_MSG_TP, "*"); // 订阅指定的 topic
        defaultMQPushConsumer.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) -> {
            for (MessageExt messageExt : list) {
                System.out.println(messageExt);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        /* 启动消费者 */
        defaultMQPushConsumer.start();
    }

}
