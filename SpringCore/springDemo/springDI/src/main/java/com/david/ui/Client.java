package com.david.ui;

import com.david.service.IAccountService;
import com.david.service.impl.AccountServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/*
* 用该类模拟一个表现层
* */
public class Client {

    /*
    * 获取spring容器的IOC，并根据id获取对象
    * 核心容器的两个接口
    * ApplicationContext    单例对象适用
    *    它在构建核心容器时，创建对象采取的策略是采用立即加载的方式。
    *    也就是说，只要一读取完配置文件马上就创建配置文件中配置的对象
    * BeanFactory           多例对象适用
    *    它在构建核心容器时，创建对象采取的是延迟加载的方式
    *    也就是说，什么时候根据id获取对象了，什么时候才创建对象
    * */
    public static void main(String[] args) {

        /*ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanConfig.xml");
        IAccountService as = (IAccountService)applicationContext.getBean("accountService");
        as.saveAccount();

        System.out.println(as);*/

        //testAccount_1();

        testAccount_2();
    }

    private static void testAccount_1() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanConfig.xml");
        IAccountService as = (IAccountService)applicationContext.getBean("accountService_1");
        as.saveAccount();

        System.out.println(as);
    }

    private static void testAccount_2() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanConfig.xml");
        IAccountService as = (IAccountService)applicationContext.getBean("accountService_2");
        as.saveAccount();

        System.out.println(as);
    }
}
