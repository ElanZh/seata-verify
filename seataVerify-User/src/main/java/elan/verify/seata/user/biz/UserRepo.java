package elan.verify.seata.user.biz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    @Transactional
    @Modifying
    @Query(value = "update User u set u.balance = u.balance - ?2 where u.id = ?1 and u.balance >= ?2")
    int updateForSubtract(int userId, int value);

}
