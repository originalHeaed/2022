package wang.study.config;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * Author:   wang
 * Date:     2022/7/17 22:15
 * function: sentinel 限流异常处理类
 */
public class CommonBlockExceptionHandler {
    public static String getF(BlockException e) {
        return e.getClass() + "you are wrong";
    }
}
