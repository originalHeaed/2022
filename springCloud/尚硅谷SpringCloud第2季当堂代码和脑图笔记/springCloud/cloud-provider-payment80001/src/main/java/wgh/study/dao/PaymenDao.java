package wgh.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wgh.study.entities.Payment;

/**
 * Author:   wang
 * Date:     2022/5/4 16:56
 * function: payment 表 dao 接口
 */
@Mapper
public interface PaymenDao {
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
