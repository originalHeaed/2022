package org.example.retryMsgSender;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.Charset;

/**
 * 异步消息重试发送器
 */
public class AsynMsgRetrySender {
    /**
     * namesrv 的 ip:port
     */
    public static String NAME_ADDR = "112.74.97.91:9876";

    /**
     * 消息 topic
     */
    public static String TOPIC = "asyncMsgRetryTopic";

    /**
     * 消息组
     */
    public static String GP = "asyncRetryGp";

    /**
     * 最大重试次数
     */
    public static int MAX_RETRY_TIMES = 5;

    public static void main(String[] args) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        /* 1. 创建生产者 */
        DefaultMQProducer producer = new DefaultMQProducer(GP);
        /* 2. 进行配置 */
        producer.setNamesrvAddr(NAME_ADDR);
        producer.setRetryTimesWhenSendAsyncFailed(MAX_RETRY_TIMES); // 异步最大重试次数
        producer.start();
        /* 3. 发送消息 */
        Message message = new Message(TOPIC, "asyncMsgTest".getBytes(Charset.forName("utf-8")));
        producer.send(message, new SendCallback() {
            /**
             * 最终发送成功回调
             * @param sendResult
             */
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("yes, you are success");
            }

            /**
             * 最终失败回调
             * @param throwable
             */
            @Override
            public void onException(Throwable throwable) {
                throwable.printStackTrace();
                System.out.println("no, you are wrong");
            }
        });
        /* 4. 关闭生产者 */
        Thread.sleep(30000);
        producer.shutdown();
    }
}
