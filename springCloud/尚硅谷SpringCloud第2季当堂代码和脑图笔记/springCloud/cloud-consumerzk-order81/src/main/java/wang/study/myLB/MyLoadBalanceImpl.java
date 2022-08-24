package wang.study.myLB;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Author:   wang
 * Date:     2022/5/23 22:25
 * function: 个人负载均衡算法 impl
 */
@Component
public class MyLoadBalanceImpl implements IMyLoadBalace{

    /**
     * 计数器
     */
    private AtomicInteger counter = null;

    /**
     * counter 计数器的最大值
     */
    private Integer MAX_VALUE = 1000000;

    /**
     * 类构造方法，将 counter 进行初始化
     */
    public MyLoadBalanceImpl() {
        counter = new AtomicInteger(0);
    }

    @Override
    public ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList) {
        if (ObjectUtils.isEmpty(serviceInstanceList)) {
            System.out.println("不存在可以使用的服务！！");
            return null;
        }
        Integer nextIndex = this.getNextIndex(serviceInstanceList.size());
        return serviceInstanceList.get(nextIndex % serviceInstanceList.size());
    }

    /**
     * 使用自旋 + cas 获取 counter 的下一个值
     * @return
     */
    private Integer getNextIndex(int modules) {
        int current;
        int next;
        for(;;) {
            current = counter.get();
            if (current >= MAX_VALUE) {
                next = current % modules + 1;
            } else {
                next = current + 1;
            }
            if (counter.compareAndSet(current, next)) return next;
        }
    }
}
