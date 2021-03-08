package com.david.service.impl;

import com.david.service.IAccountService;

import java.util.Date;

public class AccountServiceImpl_1 implements IAccountService {
    //如果是经常变化的数据，不适合注入的方式
    private String name;
    private Integer age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println("saveAccount has bena runned" + name + age);
    }
}
