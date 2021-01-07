package com.itheima.service;

import com.itheima.domain.Account;

public interface IAccountService {

    Account findAccountById(Integer accountId);

    void transfer(String sourceName, String targetName, Double money);
}
