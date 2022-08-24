package wang.study.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Copyright (C), 2015-2022, XXX有限公司
 * Author:   wang
 * Date:     2022/7/25 21:53
 * function: 产品库存 service
 * History:
 * <author>    <time>    <version>    <desc>
 * 作者姓名     修改时间     版本号        描述
 */
@FeignClient("seata-storage-server")
public interface StorageService {

    @PostMapping("/seata/descStorage")
    String descStorage(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
