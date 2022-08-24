package wang.study.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * Author:   wang
 * Date:     2022/7/25 21:58
 * function: 账户 service
 */
@FeignClient("seata-account-server")
public interface AccountService {

    @PostMapping("/seata/descAccount")
    String descAccount(@RequestParam("userId") Long userId, @RequestParam("money")BigDecimal money);
}
