package wang.study.Month06;

import java.util.Stack;

/**
 * Author:   wang
 * Date:     2022/6/8 23:26
 * function:
 */
public class First {

    /**
     * 插入湛
     */
    Stack<Integer> stackIn;

    /**
     * 删除栈
     */
    Stack<Integer> stackOut;

    public First() {
        stackIn = new Stack<Integer>();
        stackOut = new Stack<Integer>();
    }

    public void appendTail(int value) {
        stackIn.push(value);
    }

    public int deleteHead() {
        if (stackOut.isEmpty() && stackIn.isEmpty()) return -1;
        if (stackOut.isEmpty()) this.transfer();
        return stackOut.pop();
    }

    /**
     * 当 stackOut 为空时，将 stackIn 中所有元素放入 stackOut 中
     */
    private void transfer() {
        while (!stackIn.isEmpty()) {
            stackOut.push(stackIn.pop());
        }
    }
}
