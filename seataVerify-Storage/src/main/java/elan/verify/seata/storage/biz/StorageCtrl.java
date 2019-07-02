package elan.verify.seata.storage.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张一然
 * @date 2019年-07月-01号 下午7:28
 * @Description :  TODO
 */
@RestController
@RequestMapping("storage")
public class StorageCtrl {
    private final StorageRepo storageRepo;

    @Autowired
    public StorageCtrl(StorageRepo storageRepo) {
        this.storageRepo = storageRepo;
    }

    @GetMapping("subtractStorage")
    public boolean subtractStorage(int storageId, int value){
        return storageRepo.updateForSubtract(storageId, value) == 1;
    }

}
