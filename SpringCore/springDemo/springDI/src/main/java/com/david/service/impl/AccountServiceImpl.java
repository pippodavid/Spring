package com.david.service.impl;

import com.david.service.IAccountService;

import java.util.Date;

/*
* 账户的业务层实现类
* */
public class AccountServiceImpl implements IAccountService {

    //如果是经常变化的数据，不适合注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println("saveAccount is running");
    }
}
