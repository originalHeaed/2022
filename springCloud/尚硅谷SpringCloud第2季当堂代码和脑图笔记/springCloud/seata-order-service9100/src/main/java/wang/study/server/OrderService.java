package wang.study.server;

import wang.study.entity.Order;

/**
 * Copyright (C), 2015-2022, XXX有限公司
 * Author:   wang
 * Date:     2022/7/25 21:49
 * function: 订单 service 接口
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号        描述
 */
public interface OrderService {
    /**
     * 插入一条记录
     * @param order
     */
    void insertOrder(Order order);

    void updateOrderWithoutTr(Order order);
}
