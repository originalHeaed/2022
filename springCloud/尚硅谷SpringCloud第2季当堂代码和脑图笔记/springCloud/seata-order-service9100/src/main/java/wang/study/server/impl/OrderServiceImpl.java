package wang.study.server.impl;

import io.seata.spring.annotation.GlobalTransactional;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.study.dao.OrderDao;
import wang.study.entity.Order;
import wang.study.server.AccountService;
import wang.study.server.OrderService;
import wang.study.server.StorageService;

import javax.annotation.Resource;

/**
 * Author:   wang
 * Date:     2022/7/25 21:52
 * function: 订单服务实现类
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageService storageService;

    @Autowired
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    public void insertOrder(Order order) {
        if (order == null) return;
        System.out.println("插入一条状态为 0 的订单记录");
        orderDao.insertOrder(order);
        System.out.println("更新库存");
        String res = storageService.descStorage(order.getProductId(), order.getCount());
        System.out.println("res");
        String s = accountService.descAccount(order.getUserId(), order.getMoney());
        System.out.println(s);
        System.out.println("将订单状态更新为 1");
        orderDao.updateStatus(order.getUserId(), order.getProductId());
    }

    /**
     * 无事务更新 order 表
     * 用于测试脏写
     * @param order
     */
    @Override
    public void updateOrderWithoutTr(Order order) {
        orderDao.updateStatus(order.getUserId(), order.getProductId());
    }
}
