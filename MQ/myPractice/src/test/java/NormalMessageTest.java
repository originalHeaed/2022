import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class NormalMessageTest {
    /**
     * 同步发送消息
     */
    @Test
    public void test1() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr("112.74.97.91:9876");
        producer.setDefaultTopicQueueNums(2);
        producer.setProducerGroup("wangGroup");
        producer.start();
        for (int i = 0; i < 20; i++) {
            Message message = new Message("20230202Topic", "tag1", "hello".getBytes());
            message.setKeys("key: " + i);
            /* messageId: 生产者发送数据产生的唯一标识
            * offsetId: broker 生成，brokerIp + 物理分区的 offset */
            SendResult send = producer.send(message);
            System.out.println(send);
        }
        /* 关闭 producer */
        producer.shutdown();
    }

    /**
     * 异步 mq 发送消息
     */
    @Test
    public void test2() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr("112.74.97.91:9876");
        producer.setDefaultTopicQueueNums(2);
        producer.setProducerGroup("wangGroup");
        producer.start();
        for (int i = 0; i < 1; i++) {
            Message message = new Message("retryOrderlyTp2", "tag1", "hello".getBytes());
            message.setKeys("key: 10");
            /**
             * 异步发送消息，会使用异步发送执行器来执行这个发送任务（另启用一个线程）
             */
            producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    /* 固定向第一个 queue 发送消息 */
                    MessageQueue messageQueue = list.get(1);
                    return messageQueue;
                }},  "as", new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("success" + sendResult);
                }
                @Override
                public void onException(Throwable throwable) {
                    System.out.println("error" + throwable.getMessage());
                }
            });
        }
        Thread.sleep(3000);
        /* 关闭 producer */
        producer.shutdown();
    }

    /**
     * 单向 mq 发送消息
     */
    @Test
    public void test3() throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr("112.74.97.91:9876");
        producer.setDefaultTopicQueueNums(2);
        producer.setProducerGroup("wangGroup");
        producer.start();
        for (int i = 0; i < 20; i++) {
            Message message = new Message("20230202Topic", "tag1", "hello".getBytes());
            message.setKeys("key: " + i);
            producer.sendOneway(message);
        }
        /* 关闭 producer */
        producer.shutdown();
    }

    @Test
    public void test4() {
        String[] split = "69006||0000|交易成功|8880080001|121||9900|测试客户1||8|9-000199999|132000|Add|0571-26698888|021-61615550|13588877404|mail|0|0||00|60036660|".split("\\|");
        System.out.println(split.length);
        System.out.println(split[22]);
        System.out.println(Arrays.toString(split));
    }
}
