package wang.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wang.study.service.AccountService;

import java.math.BigDecimal;

/**
 * Author:   wang
 * Date:     2022/7/26 21:29
 * function: 账户表 controller
 */
@RestController
public class AccountController {

    /**
     * 账户 service
     */
    @Autowired
    private AccountService accountService;

    @RequestMapping("/seata/descAccount")
    public String updateAccount(@RequestParam("userId") Long userId, @RequestParam("money")BigDecimal money) {
        accountService.updateAccount(userId, money);
        return "yeah, you success";
    }
}
