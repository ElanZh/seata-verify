package elan.verify.seata.user.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserCtrl {

    private final UserRepo userRepo;

    @Autowired
    public UserCtrl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("subtractBalance")
    public boolean subtractBalance(int userId, int value){
        return userRepo.updateForSubtract(userId, value) == 1;
    }
}
