package org.example.testOrderMsg;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;

public abstract class AbstractSendAndRecervice implements SendAndRecervice{
    protected String addr;

    protected String topic = "normalMsg";

    protected String groupName;

    protected void test() throws InterruptedException, MQBrokerException, RemotingException, MQClientException {
        /* 发送消息 */
        this.send();
        Thread.sleep(5000);
        System.out.print("\\033[H\\033[2J");
        System.out.flush();
        /* 接受消息 */
        this.reciver();
    }
}
