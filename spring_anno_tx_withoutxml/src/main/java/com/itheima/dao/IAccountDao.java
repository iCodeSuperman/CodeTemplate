package com.itheima.dao;

import com.itheima.domain.Account;

public interface IAccountDao {
     Account findAccountById(Integer accountId);

     Account findAccountByName(String accountName);

     void updateAccount(Account account);
}
