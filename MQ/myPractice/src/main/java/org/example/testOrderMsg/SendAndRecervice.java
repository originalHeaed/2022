package org.example.testOrderMsg;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

public interface SendAndRecervice {
    /**
     * 发送消息
     */
    void send() throws MQClientException, MQBrokerException, RemotingException, InterruptedException;

    /**
     * 接受消息
     */
    void reciver() throws MQClientException, MQBrokerException, RemotingException, InterruptedException;
}
