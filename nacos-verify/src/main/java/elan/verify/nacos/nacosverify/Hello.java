package elan.verify.nacos.nacosverify;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张一然
 * @date 2019年-08月-12号 上午10:46
 */
@RestController
@RequestMapping("hello")
@Configuration
public class Hello {
    @Value("${common.test}")
    private String test;
    @GetMapping("testConfigCenter")
    public String testConfigCenter(){
        return test;
//        return "hhhhhhh";
    }
}
