package org.example.testTransactionMsg;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.Charset;
import java.util.concurrent.*;

/**
 * 事务消息生产者
 */
public class TransactionMsgProducer {
    /**
     * 生产者 groupname
     */
    public static final String TRANSACTION_MSG_GP = "transactionGp";

    /**
     * 消息的 topic
     */
    public static final String TRANSACTION_MSG_TP = "transactionTp";

    public static void main(String[] args) throws MQClientException, InterruptedException {
        /* 1.创建生产者，注意这里使用 MQ事务消息生产者 */
        TransactionMQProducer transactionMsgProducer = new TransactionMQProducer(TRANSACTION_MSG_GP);
        /* 2.对事务生产者进行配置 */
        transactionMsgProducer.setNamesrvAddr("112.74.97.91:9876");
        transactionMsgProducer.setTransactionListener(new TestTransactionMsgImp()); // 指定事务消息监听对象
        /* 创建线程池 */
        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2000), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("thread.setName(client-transaction-msg-check-thread);\n");
                return thread;
            }
        });
        transactionMsgProducer.setExecutorService(executorService);
        /* 3. 启动生产者 */
        transactionMsgProducer.start();
        /* 发送事务消息 */
        String[] tags = {"TAGA", "TAGB", "TAGC"};
        for (int i = 0; i < 3; i++) {
            Message m = new Message(TRANSACTION_MSG_TP, tags[i], ("test" + i).getBytes(Charset.forName("utf-8")));
            m.setKeys(String.valueOf(i));
            transactionMsgProducer.sendMessageInTransaction(m, null); // 发送消息
        }
    }
}
