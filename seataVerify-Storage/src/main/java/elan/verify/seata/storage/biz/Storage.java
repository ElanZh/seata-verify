package elan.verify.seata.storage.biz;

import javax.annotation.processing.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 张一然
 * @date 2019年-07月-01号 下午7:30
 * @Description :  TODO
 */
@Entity
@Table(name = "T_STORAGE")
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class Storage {
    @Id
    @GeneratedValue(generator = "SQ_STORAGE")
    private Integer id;

    private Integer stock;
}
