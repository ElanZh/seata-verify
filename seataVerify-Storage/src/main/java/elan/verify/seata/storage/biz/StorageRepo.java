package elan.verify.seata.storage.biz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface StorageRepo extends JpaRepository<Storage, Integer> {
    @Transactional
    @Modifying
    @Query("update Storage s set s.stock = s.stock - ?2 where s.id = ?1 and s.stock >= ?2")
    int updateForSubtract(int id, int value);
}
