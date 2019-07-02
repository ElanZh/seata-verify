package elan.verify.seata.biz.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张一然
 * @date 2019年-07月-02号 上午11:08
 * @Description :  TODO
 */
@RestController
@RequestMapping("orderBiz")
public class OrderBizCtrl {
    private final OrderService orderService;

    @Autowired
    public OrderBizCtrl(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("createOrder")
    public boolean createOrder(int userId){
        return orderService.createOrder(userId);
    }
}
