package elan.verify.seata.user.biz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER")
@lombok.Data
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "SQ_USER")
    private Integer id;

    private Integer balance;
}
