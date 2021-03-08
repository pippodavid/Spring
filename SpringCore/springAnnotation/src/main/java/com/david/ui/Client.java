package com.david.ui;

import com.david.dao.IAccountDao;
import com.david.service.IAccountService;
import com.david.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/*
* 用该类模拟一个表现层
* */
public class Client {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanConfig.xml");
        IAccountService as = (IAccountService)applicationContext.getBean("accountServiceImpl");

        //as.saveAccount();
    }
}
