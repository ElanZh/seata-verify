package elan.verify.seata.order.biz;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 张一然
 * @date 2019年-07月-02号 上午10:14
 * @Description :  TODO
 */
@FeignClient(value = "seataVerify-user")
public interface UserFeign {

    /**
     * @description
     * @param userId
     * @param value
     * @return boolean
     * @author 张一然
     * @date 2019/7/2 下午1:55
     */
    @RequestMapping(method = RequestMethod.GET, value = "user/subtractBalance")
    boolean subtractBalance(@RequestParam("userId") int userId,@RequestParam("value") int value);
}
