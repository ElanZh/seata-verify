package elan.verify.seata.order.biz;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张一然
 * @date 2019年-07月-01号 下午7:56
 * orderctrl
 */
@RestController
@RequestMapping("order")
public class OrderCtrl {
    private final UserFeign userFeign;
    private final OrderRepo orderRepo;

    @Autowired
    public OrderCtrl(UserFeign userFeign, OrderRepo orderRepo) {
        this.userFeign = userFeign;
        this.orderRepo = orderRepo;
    }

    @GetMapping("create")
    @Transactional(rollbackFor = Exception.class)
    public boolean create(int userId){
        // 总价定死150
        final int amount = 150;
        // 减扣用户余额
        if (!userFeign.subtractBalance(userId, amount)) {
            return false;
        }

        // 创建订单
        Order o = new Order();
        o.setUserId(userId);
        o.setAmount(amount);
        orderRepo.save(o);
        return true;
    }
}
