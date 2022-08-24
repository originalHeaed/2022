package wang.study.service;

/**
 * Author:   wang
 * Date:     2022/7/26 21:52
 * function: t_storage 表 service 接口
 */
public interface StorageService {
    /**
     * 根据 productId 更新 t_storage 表中对应记录的数据
     * @param productId
     * @param total
     */
    void updateStorage(Long productId, Integer total);
}
