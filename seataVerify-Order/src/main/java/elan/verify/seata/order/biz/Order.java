package elan.verify.seata.order.biz;

import javax.persistence.*;

/**
 * @author 张一然
 * @date 2019年-07月-01号 下午7:56
 * @Description :  TODO
 */
@Entity
@Table(name = "T_ORDER")
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer amount;
}
