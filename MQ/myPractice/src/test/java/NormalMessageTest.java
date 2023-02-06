import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

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
        for (int i = 0; i < 20; i++) {
            Message message = new Message("20230202Topic", "tag1", "hello".getBytes());
            message.setKeys("key: " + i);
            /**
             * 异步发送消息，会使用异步发送执行器来执行这个发送任务（另启用一个线程）
             */
            producer.send(message, new SendCallback() {
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
}
