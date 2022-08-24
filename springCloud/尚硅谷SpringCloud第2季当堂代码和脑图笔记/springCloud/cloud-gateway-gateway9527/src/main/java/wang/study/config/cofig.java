package wang.study.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:   wang
 * Date:     2022/5/31 20:50
 * function:
 */
@Configuration
public class cofig {
    @Bean
    public RouteLocator customRouteLocator2(RouteLocatorBuilder builder)
    {
        RouteLocatorBuilder.Builder routes = builder.routes();
        return routes.route("test", r -> r.path("/guoj").uri("http://news.baidu.com/guoji")).build();
    }
}
