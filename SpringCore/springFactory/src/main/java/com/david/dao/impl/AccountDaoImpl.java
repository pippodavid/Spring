package com.david.dao.impl;

import com.david.dao.IAccountDao;

/*
* 账户的持久层接口实现
* */
public class AccountDaoImpl implements IAccountDao {

    public void saveAccount() {
        System.out.println("Account has been saved...");
    }
}
