package wgh.study.service;

import org.apache.ibatis.annotations.Param;
import wgh.study.entities.Payment;

/**
 * Author:   wang
 * Date:     2022/5/4 17:07
 * function: 支付订单 service 接口
 */
public interface PaymentService {
    /**
     * 插入一条 payment 记录
     * @param payment
     * @return
     */
    public int create(Payment payment);

    /**
     * 查询一条 payment 记录
     * @param id
     * @return
     */
    public Payment selectById(@Param("id") Long id);
}
