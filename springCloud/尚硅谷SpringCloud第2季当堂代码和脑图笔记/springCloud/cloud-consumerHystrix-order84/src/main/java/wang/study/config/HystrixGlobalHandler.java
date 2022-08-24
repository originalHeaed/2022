package wang.study.config;

/**
 * Author:   wang
 * Date:     2022/5/28 23:20
 * function: 全局服务降级默认处理方法
 */
public class HystrixGlobalHandler {
    public String consumerGlobalHandler() {
        return "global error!!";
    }
}
