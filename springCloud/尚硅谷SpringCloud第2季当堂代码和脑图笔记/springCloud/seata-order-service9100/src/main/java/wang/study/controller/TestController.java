package wang.study.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wang.study.entity.Order;
import wang.study.server.OrderService;

import java.math.BigDecimal;

/**
 * Author:   wang
 * Date:     2022/7/25 22:09
 * function:
 */
@RestController
public class TestController {

    @Autowired
    private OrderService orderService;

    /**
     * 客户下订单
     * @return
     */
    @RequestMapping("/client/order")
    public String clientOrder() {
        Order order = new Order();
        order.setUserId(1l);
        order.setProductId(1l);
        order.setCount(10);
        order.setMoney(new BigDecimal(10));
        orderService.insertOrder(order);
        return "yes, you are success";
    }


    /**
     * @return
     */
    @RequestMapping("/client/udateOrder")
    public String updateOrder() {
        Order order = new Order();
        order.setUserId(1l);
        order.setProductId(1l);
        order.setCount(10);
        order.setMoney(new BigDecimal(10));
        orderService.updateOrderWithoutTr(order);
        return "yes, you are success";
    }
}
