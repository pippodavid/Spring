package com.david.ui;

import com.david.factory.BeanFactory;
import com.david.service.IAccountService;
import com.david.service.impl.AccountServiceImpl;

import javax.jws.WebService;

/*
* 用该类模拟一个表现层
* */
@WebService
public class Client {

    public static void main(String[] args) {

        //IAccountService accountService = new AccountServiceImpl();
        IAccountService accountService = (IAccountService) BeanFactory.getBean("accountService");
        accountService.saveAccount();

        for (int i = 0; i < 5; i++) {
            IAccountService as = (IAccountService) BeanFactory.getBean("accountService");
            System.out.println(as);
            as.saveAccount();
        }
    }
}
