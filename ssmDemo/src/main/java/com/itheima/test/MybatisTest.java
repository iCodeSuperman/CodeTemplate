package com.itheima.test;

import com.itheima.dao.AccountDao;
import com.itheima.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {

    InputStream in;
    SqlSessionFactoryBuilder builder;
    SqlSessionFactory factory;
    SqlSession session;
    AccountDao accountDao;

    @Before
    public void init() throws Exception {
        // 1. 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        // 2. 创建SqlSessionFactory的构建者
        builder = new SqlSessionFactoryBuilder();
        // 3. 使用构建者创建工厂对象SqlSessionFactory
        factory = builder.build(in);
        // 4. 使用SqlSessionFactory生成SqlSession对象
        session = factory.openSession();
        // 5. 使用SqlSession创建dao接口的代理对象
        accountDao = session.getMapper(AccountDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.commit();
        // 6. 释放资源
        session.close();
        in.close();
    }

    /**
     * 测试查询所有账户
     */
    @Test
    public void testFindAll(){
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * 测试保存账户
     */
    @Test
    public void testSaveAccount(){
        Account account = new Account();
        account.setName("徐彦祖");
        account.setMoney(100000.0);
        accountDao.saveAccount(account);

        testFindAll();
    }
}
