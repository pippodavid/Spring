package com.david.service.impl;

import com.david.service.IAccountService;

/*
* 账户的业务层实现类
* */
public class AccountServiceImpl implements IAccountService {

    public AccountServiceImpl() {
        System.out.println("object is created");
    }

    public void init() {
        System.out.println("object has been initialized");
    }

    public void destroy() {
        System.out.println("objcet has been destoryed");
    }

    public void saveAccount() {
        System.out.println("saveAccount is running");
    }
}
