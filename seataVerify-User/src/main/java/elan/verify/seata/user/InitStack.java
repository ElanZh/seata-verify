package elan.verify.seata.user;

import elan.verify.seata.user.biz.User;
import elan.verify.seata.user.biz.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
public class InitStack implements ApplicationRunner {
    private final UserRepo userRepo;

    @Autowired
    public InitStack(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 初始化设置用户余额200
        User u;
        if (userRepo.count() == 0) {
            u = new User();
            u.setBalance(200);
        } else {
            u = userRepo.findAll(PageRequest.of(0, 1)).getContent().get(0);
            u.setBalance(200);
        }
        userRepo.save(u);

    }
}
