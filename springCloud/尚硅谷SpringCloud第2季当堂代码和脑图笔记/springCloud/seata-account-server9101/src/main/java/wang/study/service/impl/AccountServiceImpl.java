package wang.study.service.impl;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.study.dao.AccountDao;
import wang.study.service.AccountService;

import java.math.BigDecimal;

/**
 * Author:   wang
 * Date:     2022/7/26 21:26
 * function: accountservice 的实现类
 */
@Service
public class AccountServiceImpl implements AccountService {

    /**
     * account 表 dao
     */
    @Autowired
    private AccountDao accountDao;

    /**
     * 根据 userId 更新对应记录的 money
     * @param userId
     * @param money
     */
    @Override
    public void updateAccount(Long userId, BigDecimal money) {
        System.out.println("更新账户余额 start");
        int i = 10/0;
        accountDao.updateAccount(userId, money);
        System.out.println("更新账户余额 end");
    }
}
