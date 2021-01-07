package com.itheima.dao.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * 1. 写完函数的查询语句
 * 2. 配置JdbcTemplateConfig类，注入由方法提供jdbcTemplate
 */

@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

    @Resource(name="jdbcTemplate")
    // @Autowired
    private JdbcTemplate jdbcTemplate;

    public Account findAccountById(Integer accountId) {
        List<Account> accounts = jdbcTemplate.query(
                "select * from account where id = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                accountId
        );
        return accounts.isEmpty() ? null : accounts.get(0);
    }

    public Account findAccountByName(String accountName) {
        List<Account> accounts = jdbcTemplate.query(
                "select * from account where name = ?",
                new BeanPropertyRowMapper<Account>(Account.class),
                accountName
        );
        if(accounts == null || accounts.size() == 0){
            return null;
        }
        if(accounts.size() > 1){
            throw new RuntimeException("数据集不唯一！");
        }
        return accounts.get(0);
    }

    public void updateAccount(Account account) {
        jdbcTemplate.update(
            "update account set name = ?, money = ? where id = ?",
                account.getName(), account.getMoney(), account.getId()
        );
    }
}
