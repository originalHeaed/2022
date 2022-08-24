package wang.study.controller;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wang.study.service.StorageService;

/**
 * Author:   wang
 * Date:     2022/7/26 21:56
 * function:
 */
@RestController
public class StorageController {

    /**
     * storage serice 对象
     */
    @Autowired
    private StorageService storageService;

    @RequestMapping("/seata/descStorage")
    public String updateStorage(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        storageService.updateStorage(productId, count);
        return "yeah, descStorage success";
    }
}
