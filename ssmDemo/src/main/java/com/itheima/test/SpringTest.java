package com.itheima.test;

import com.itheima.service.AccountService;
import jdk.nashorn.internal.ir.CallNode;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    /**
     * 测试第一步：
     *  spring框架测试service层是否配置正确
     */
    @Test
    public void testSpring01(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        AccountService as = ac.getBean("accountService", AccountService.class);
        as.findAll();
        as.saveAccount(null);
    }
}
