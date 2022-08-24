package wang.study.filter;

import io.netty.util.internal.ObjectUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Author:   wang
 * Date:     2022/5/31 21:57
 * function: springcloud gateway filter
 */
@Component
public class MyGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("老王吧学 springcloud gateway");

        String uname = exchange.getRequest().getQueryParams().getFirst("username");
        if (ObjectUtils.isEmpty(uname)) {
            System.out.println("嘿嘿嘿，你没有设置 username 的值哦");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 用于控制优先级
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
