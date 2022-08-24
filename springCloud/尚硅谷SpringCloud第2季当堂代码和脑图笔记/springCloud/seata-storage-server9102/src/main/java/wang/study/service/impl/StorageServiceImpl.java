package wang.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.study.dao.StorageDao;
import wang.study.service.StorageService;

/**
 * Author:   wang
 * Date:     2022/7/26 21:53
 * function: service 接口实现类
 */
@Service
public class StorageServiceImpl implements StorageService {

    /**
     * t_storage dao
     */
    @Autowired
    private StorageDao storageDao;

    @Override
    public void updateStorage(Long productId, Integer total) {
        System.out.println("修改库存 start");
        storageDao.updateStorage(productId, total);
        System.out.println("修改库存 end");
    }
}
