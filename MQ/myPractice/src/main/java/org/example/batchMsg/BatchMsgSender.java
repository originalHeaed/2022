package org.example.batchMsg;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.example.AbstractSender;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;

/**
 * 批量消息发送器
 */
public class BatchMsgSender extends AbstractSender {

    /**
     * 发送消息的 topic
     */
    public static String TOPIC = "batchMsgTp";

    /**
     * 发送消息的 group
     */
    public static String GROUP = "batch";

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        /* 1. 创建消息生产者 */
        DefaultMQProducer producer = new DefaultMQProducer(GROUP);
        producer.setDefaultTopicQueueNums(2);
        producer.setNamesrvAddr(NAME_ADDR);
        /* 2. 启动生产消息者 */
        producer.start();
        List<Message> list = new LinkedList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new Message(TOPIC, "巍峨谔谔谔谔谔谔谔谔谔谔谔谔呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃呃".getBytes(Charset.forName("utf-8"))));
        }
        /* 3. 开始批量发送消息 */
        MessageListSplit split = new MessageListSplit(list, 10 * 1024);
        int i = 1;
        while (split.hasNext()) {
            List<Message> next = split.next();
            SendResult send = producer.send(next);
            System.out.println("成功发送第 " + i++ + " 批消息:" + send);
        }
        /* 4. 关闭 producer */
        producer.shutdown();
    }
}
