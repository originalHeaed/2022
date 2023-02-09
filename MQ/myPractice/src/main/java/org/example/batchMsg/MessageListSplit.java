package org.example.batchMsg;

import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 批量发送消息集合分割器
 */
public class MessageListSplit implements Iterator<List<Message>> {
    /**
     * 数据源
     */
    private final List<Message> resource;

    /**
     * 每次发送消息的最大 size
     */
    private final int MAX_SZIE;

    /**
     * list 中第一个没有被消费的 message 的 index
     */
    private int curr_index;

    public MessageListSplit(List<Message> resource, int maxSize) {
        assert resource == null: "resource is not allowed null";
        assert maxSize <= 0: "maxSize is not allowed less or equal zero";
        this.resource = resource;
        this.MAX_SZIE = maxSize;
        this.curr_index = 0;
    }

    /**
     * 判断是否存在下一个
     * @return
     */
    @Override
    public boolean hasNext() {
        return curr_index < resource.size();
    }

    
    @Override
    public List<Message> next() {
        int total = 0; // 这一批需要发送的消息总大小
        int next = curr_index; // 使用滑动窗口计算当前 [curr_index, next] 之间所有消息的大小
        for (;next < resource.size(); ++next) {
            int tem = calculatorMessageSize(resource.get(next));
            if ((total + tem) > MAX_SZIE) {
                break;
            }
            total += tem;
        }
        if (curr_index >= resource.size()) return null;
        if (curr_index == next) {
            // 出现这种情况表示，某个消息的大小大于 MAX_SIZE，因此需要单独将该消息进行提交
            next++;
        }
        List<Message> subList = resource.subList(curr_index, next); // 返回一个视图，无法修改
        curr_index = next;
        return subList;
    }

    /**
     * 计算消息的大小
     */
    private int calculatorMessageSize(Message message) {
        int res = 0;
        /* 计算消息的 topic 大小 */
        res += message.getTopic().getBytes().length;
        /* 计算消息属性的大小 */
        Map<String, String> properties = message.getProperties();
        for (Map.Entry<String, String> val : properties.entrySet()) {
            res += val.getKey().getBytes().length;
            res += val.getValue().getBytes().length;
        }
        /* 消息固定 20 字节大小 */
        res += 20;
        /* 计算消息体大小 */
        res += message.getBody().length;
        return res;
    }
}
