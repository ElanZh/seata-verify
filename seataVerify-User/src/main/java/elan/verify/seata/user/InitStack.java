package elan.verify.seata.user;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import elan.verify.seata.user.biz.User;
import elan.verify.seata.user.biz.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Configuration
public class InitStack implements ApplicationRunner {
    @Autowired
    private DruidDataSource dataSource;

    private final UserRepo userRepo;

    @Autowired
    public InitStack(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.warn("开始执行初始化任务！");
        log.warn(dataSource.getUrl());
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
        log.warn("初始化任务执行完毕！");

    }
}
