package wang.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * Copyright (C), 2015-2022, XXX有限公司
 * Author:   wang
 * Date:     2022/7/26 21:16
 * function: t_account dao
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号        描述
 */
@Mapper
public interface AccountDao {
    /**
     * 根据 userId 更新账户表的余额
     * @param userId
     * @param money
     */
    void updateAccount(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
