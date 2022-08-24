package wang.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.study.entity.Order;

/*********************************************************
 * 文件名称：OrderDao.java
 * 软件版权: 恒生电子股份有限公司
 * 系统名称：期货保证金（Java 版）
 * 系统版本：5.0
 * 模块名称：wang.study.dao
 * 功能说明：订单 dao
 * 开发人员 @author：wanggh31690
 * 开发时间 @date：2022/7/25 18:00
 * 修改记录：程序版本  修改日期  修改人员  修改单号  修改说明
 *********************************************************/
@Mapper
public interface OrderDao {
    /**
     * 插入一条 order 记录
     * @param order
     */
    void insertOrder(Order order);

    /**
     * 将对应 userId 和 productId 的订单记录钻状态更新为 1-已完结
     * @param userId
     * @param productId
     * @return
     */
    int updateStatus(@Param("userId") long userId, @Param("productId") long productId);
}
