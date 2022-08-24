package wang.study.conroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author:   wang
 * Date:     2022/6/5 14:32
 * function:
 */
@RestController
@RefreshScope
public class ConfigController {
    @Value("${hello.lululu}")
    private String configInfo;

    @Value("${server.port}")
    private String port;

    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return configInfo + "port:" + port;
    }
}
