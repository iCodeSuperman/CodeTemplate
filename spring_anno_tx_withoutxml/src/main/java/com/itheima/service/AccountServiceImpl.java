package com.itheima.service;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 1. 写完业务方法
 * 2. 注入由accountDao类提供的accountDao
 * 3. 配置TransactionConfig类
 * 4. 在SpringConfiguration类中开启事务支持
 * 5. 在业务层开启事务
 */
@Service("accountService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) //只读型事务配置
public class AccountServiceImpl implements IAccountService{

    @Resource(name="accountDao")
    //@Autowired
    private IAccountDao accountDao;

    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false) //读写性事务配置
    public void transfer(String sourceName, String targetName, Double money) {
        System.out.println("transfer is running ...");
        Account source = accountDao.findAccountByName(sourceName);
        Account target = accountDao.findAccountByName(targetName);
        source.setMoney(source.getMoney() - money);
        target.setMoney(target.getMoney() + money);
        accountDao.updateAccount(source);
        // int i = 1 / 0;
        accountDao.updateAccount(target);
    }
}
