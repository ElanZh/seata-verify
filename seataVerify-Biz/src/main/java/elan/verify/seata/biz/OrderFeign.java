package elan.verify.seata.biz;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author 张一然
 * @date 2019年-07月-02号 下午1:32
 * @Description :  TODO
 */
@FeignClient(value = "seataVerify-order")
public interface OrderFeign {
    @RequestMapping(method = RequestMethod.GET, value = "order/create")
    boolean createOrder(int userId);
}
