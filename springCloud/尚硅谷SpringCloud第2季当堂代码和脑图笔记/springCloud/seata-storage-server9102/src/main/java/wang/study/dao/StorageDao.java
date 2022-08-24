package wang.study.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Author:   wang
 * Date:     2022/7/26 21:45
 * function: storage dao
 */
@Mapper
public interface StorageDao {
    /**
     * 根据 productId 更新 t_product 表中数据
     * @param productId
     * @param total
     */
    void updateStorage(@Param("productId") Long productId, @Param("total") Integer total);
}
