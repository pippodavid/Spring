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
        /*1.获取核心容器对象
        获取对象的方法有三种，分别对应ApplicztionContext的三个实现类
        ClassPathXmlApplicationContext       指定配置文件路径（文件名）
        FileSystemXmlApplicationContext      指定配置文件的绝对地址
        AnnotationConfigApplicationContext   注解配置获取的类对象
        * */
        //实际比较少用FileSystemXmlApplicationContext，因为这个依赖文件绝对，而ClassPathXmlApplicationContext
        //和通常是读和代码放在一起的一个配置文件，比较不容易出问题
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanConfig.xml");
        //ApplicationContext applicationContext = new FileSystemXmlApplicationContext("D:\\Java\\JavaDemo\\javaTest\\springDemo\\src\\main\\resources\\beanConfig.xml");
        //2.根据id获取bean对象
        IAccountService accountService = (IAccountService)applicationContext.getBean("accountService");
        IAccountDao accountDao = applicationContext.getBean("accountDao", IAccountDao.class);
        System.out.println(accountService);
        System.out.println(accountDao);
        //accountService.saveAccount();
    }
}
