package wgh.study.service.impl;

import org.springframework.stereotype.Service;
import wgh.study.dao.PaymenDao;
import wgh.study.entities.Payment;
import wgh.study.service.PaymentService;

import javax.annotation.Resource;

/**
 * Author:   wang
 * Date:     2022/5/4 17:08
 * function: 支付订单 impl 类
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     * payment 表 dao
     */
    @Resource
    private PaymenDao paymenDao;

    /**
     * 插入一笔记录
     * @param payment
     * @return
     */
    @Override
    public int create(Payment payment) {
        return paymenDao.create(payment);
    }

    /**
     * 查询一笔记录
     * @param id
     * @return
     */
    @Override
    public Payment selectById(Long id) {
        return paymenDao.selectById(id);
    }
}
