package wang.study.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author:   wang
 * Date:     2022/5/24 23:05
 * function: OpenFeign 日志 config
 */
@Configuration
public class OpenFeignConfig
{
    @Bean
    Logger.Level feignLoggerLevel()
    {
        return Logger.Level.FULL;
    }
}
