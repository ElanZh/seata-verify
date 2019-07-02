package elan.verify.seata.storage;

import elan.verify.seata.storage.biz.Storage;
import elan.verify.seata.storage.biz.StorageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

/**
 * @author 张一然
 * @date 2019年-07月-01号 下午7:49
 * @Description :  TODO
 */
@Component
public class InitStack implements ApplicationRunner {

    private final StorageRepo storageRepo;

    @Autowired
    public InitStack(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Storage s;
        if (storageRepo.count()==0){
            s = new Storage();
            s.setStock(1);
        } else {
            s = storageRepo.findAll(PageRequest.of(0, 1)).getContent().get(0);
            s.setStock(1);
        }
        storageRepo.save(s);
    }
}
