package com.david.factory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*
* 一个创建Bean对象的工厂
*
* Bean此处理解为可重用组件的含义
* JavaBean表示用java语言编写的可重用类，范围大于实体类
* 他是创建我们的service和dao对象的
* 第一个；
*     需要一个配置文件来配置我们的service和dao，配置的内容，
*     唯一标识=全限定类名(对应的关系相当于：key=value)
* 第二个：
*     通过读取配置文件中配置的内容，反射创建对象
* 我们的配置文件可以是xml也可以是properties
* */
public class BeanFactory {
    //定义一个properties对象
    private static Properties props;

    //定义一个Map,用于存放我们要创建的对象，我们把它称为容器
    private static Map<String, Object> beans;

    //使用静态代码块为Properties对象赋值
    static {
        //实例化对象
        props = new Properties();
        InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("bean.properties");
        try {
            props.load(is);
            beans = new HashMap<String, Object>();
            //取出配置文件中所有的key
            Enumeration keys = props.keys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement().toString();
                //根据key获取value
                String beanPath = props.getProperty(key);
                //反射创建对象
                Object value = Class.forName(beanPath).newInstance();
                beans.put(key, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    * 根据bean的名称获取对象
    * 这里获取的对象就是单例的
    * */
    /*public static Object getBean(String beanName) {
        return beans.get(beanName);
    }*/

    /*
    * 根据bean的名称获取bean对象
    * */
    public static Object getBean(String beanName) {
        Object bean = "";
        String beanPath = props.getProperty(beanName);
        try {
            bean = Class.forName(beanPath).newInstance();  //这里表示每次都会调用默认构造函数创建对象
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bean;
    }
}
