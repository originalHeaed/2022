package wang.study.server;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * Author:   wang
 * Date:     2022/7/13 22:57
 * function:
 */
@Service
public class ServerTest {

    @SentinelResource(value = "common")
    public String test() {
        return "I am Common";
    }
}
