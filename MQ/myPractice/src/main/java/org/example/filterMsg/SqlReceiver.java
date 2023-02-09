package org.example.filterMsg;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * sql 过滤器测试消费者
 */
public class SqlReceiver {
    /**
     * namesrv 地址和端口
     */
    public static String NAME_ADDR = "112.74.97.91:9876";

    /**
     * topic
     */
    public static String TOPIC = "sqlFilterTP";

    /**
     * group
     */
    public static String GROUP = "sqlFilterGP";

    public int total = 0;

    public void test() throws MQClientException {
        /* 1. 定义消费者 */
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(GROUP);
        /* 2. 进行配置 */
        defaultMQPushConsumer.setNamesrvAddr(NAME_ADDR);
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.subscribe(TOPIC, MessageSelector.bySql("sex = '男'"));
        defaultMQPushConsumer.registerMessageListener((List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) -> {
            total += list.size();
            System.out.println("当前总消费记录数：" + total);
            for (MessageExt messageExt : list) System.out.println(messageExt.getProperty("sex"));
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        /* 3. 启动消费者 */
        defaultMQPushConsumer.start();

    }


    public static void main(String[] args) throws MQClientException {
        SqlReceiver sqlReceiver = new SqlReceiver();
        sqlReceiver.test();
    }
}
