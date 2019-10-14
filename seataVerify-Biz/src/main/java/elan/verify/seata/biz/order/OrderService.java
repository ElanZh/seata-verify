package elan.verify.seata.biz.order;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author 张一然
 * @date 2019年-07月-02号 下午1:24
 * @Description :  TODO
 */
@Service
public class OrderService {
    private final static Logger LOG = LoggerFactory.getLogger(OrderService.class);
    private final OrderFeign orderFeign;
    private final StorageFeign storageFeign;

    public OrderService(OrderFeign orderFeign, StorageFeign storageFeign) {
        this.orderFeign = orderFeign;
        this.storageFeign = storageFeign;
    }

    @GlobalTransactional(timeoutMills = 300000)
    boolean createOrder(int userId) {
        LOG.info("business Service Begin ... xid: " + RootContext.getXID());
        // 减扣库存
        if (!storageFeign.subtractStorage(1,1)) {
            throw new RuntimeException("减扣库存失败，下单失败");
        }
        // 创建订单
        if (!orderFeign.createOrder(userId)) {
            throw new RuntimeException("创建订单失败");
        }
        return true;
    }
}
