package wang.study.myLB;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * Author:   wang
 * Date:     2022/5/23 22:10
 * function: 自定义负载均衡接口
 */
public interface IMyLoadBalace {
    /**
     * 使用轮询算法获取服务实例
     * @param serviceInstanceList
     * @return
     */
    ServiceInstance getServiceInstance(List<ServiceInstance> serviceInstanceList);
}
