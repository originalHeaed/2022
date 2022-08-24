package wang.study.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Author:   wang
 * Date:     2022/7/25 22:15
 * function:
 */
@Configuration
@MapperScan({"wang.study.dao"})
public class MyBatisConfig {
}
