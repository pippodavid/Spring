package com.david.service.impl;

import com.david.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService{

    public void saveAccount() {
        System.out.println("saveAccout is executed");
    }

    public void updateAccount(int i) {
        System.out.println("updateAccount is executed"+i);
    }

    public int deleteAccount() {
        System.out.println("deleteAccount is executed");
        return 0;
    }
}
