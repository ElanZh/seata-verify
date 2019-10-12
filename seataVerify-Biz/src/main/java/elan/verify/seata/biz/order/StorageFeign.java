package elan.verify.seata.biz.order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 张一然
 * @date 2019年-07月-02号 下午1:26
 * @Description :  TODO
 */
@FeignClient(value = "seata-verify-storage")
public interface StorageFeign {
    @RequestMapping(method = RequestMethod.GET, value = "storage/subtractStorage")
    boolean subtractStorage(@RequestParam("storageId") int storageId,@RequestParam("value") int value);
}
