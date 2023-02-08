package org.example.testTransactionMsg;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class TestTransactionMsgImp implements TransactionListener {
    /* 消息成功发送到 broker 后将会执行该方法 */
    @Override
    public LocalTransactionState executeLocalTransaction(Message message, Object o) {
        System.out.println("消息成功提交至 broker，变成了半消息");
        /* 返回事务执行结果给 broker */
        switch (message.getTags()) {
            case "TAGA":
                return LocalTransactionState.COMMIT_MESSAGE; // 模拟事务执行成功
            case "TAGB":
                return LocalTransactionState.ROLLBACK_MESSAGE; // 模拟事务执行失败进行回滚
            default:
                return LocalTransactionState.UNKNOW; // 模拟事务执行结果未知
        }
    }

    /**
     * 当 executeLocalTransaction 返回结果为 UNKONW 时，broker 将会进行回查，回查将会调用该方法
     * @param messageExt
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
