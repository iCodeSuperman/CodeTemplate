package com.itheima.dao;

import com.itheima.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao")
public interface AccountDao {

    /**
     * 查找所有账户
     * @return
     */
    @Select("select * from account")
    List<Account> findAll();

    /**
     * 保存账户
     * @param account
     */
    @Insert("insert into account (name, money) values(#{name}, #{money})")
    void saveAccount(Account account);

}
