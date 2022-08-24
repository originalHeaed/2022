package wang.study.service;

import java.math.BigDecimal;

/**
 * Author:   wang
 * Date:     2022/7/26 21:25
 * function:
 */
public interface AccountService {
    /**
     * 根据 userId 更新 money
     * @param userId
     * @param money
     */
    void updateAccount(Long userId, BigDecimal money);
}
