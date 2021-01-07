package com.itheima.testSpring;


import com.itheima.config.SpringConfiguration;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 测试类
 *  1. pom.xml 注入junit和spring-test
 *  2. @RunWith() 注解
 *  3. @ContextConfiguration(classes = )
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class MySpringTest {
    @Resource(name = "accountService")
    private IAccountService as;

    @Test
    public void transferTest(){
        as.transfer("aaa", "bbb",100.0);
    }
}
