package org.example.retryMsgSender;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;

/**
 * 同步消息重试发送器
 */
public class SyncMsgRetrySender {
    /**
     * namesrv 的 ip:port
     */
    public static String NAME_ADDR = "112.74.97.91:9876";

    /**
     * 消息 topic
     */
    public static String TOPIC = "syncMsgRetryTopic";

    /**
     * 消息组
     */
    public static String GP = "syncRetryGp";

    /**
     * 最大重试次数
     */
    public static int MAX_RETRY_TIMES = 5;

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        /* 1. 构建生产者对象 */
        DefaultMQProducer producer = new DefaultMQProducer(GP);
        /* 2. 进行配置 */
        producer.setNamesrvAddr(NAME_ADDR);
        producer.setRetryTimesWhenSendFailed(MAX_RETRY_TIMES); // 同步消息最大重试次数
        producer.setSendMsgTimeout(4000); // 超时时间（默认为 3 s）
        producer.start();
        /* 3. 开始生成消息 */
        Message message = new Message(TOPIC, "syncRetry".getBytes(Charset.forName("utf-8")));
        producer.send(message);
        /* 4. 关闭生产者 */
        producer.shutdown();
    }
}
