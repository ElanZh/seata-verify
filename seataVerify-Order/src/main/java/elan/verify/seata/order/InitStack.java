package elan.verify.seata.order;

import elan.verify.seata.order.biz.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author 张一然
 * @date 2019年-07月-02号 上午10:02
 * @Description :  TODO
 */
@Component
public class InitStack implements ApplicationRunner {
    private final OrderRepo orderRepo;

    @Autowired
    public InitStack(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 启动删除所有订单
        orderRepo.deleteAllByIdIsNotNull();
    }
}
