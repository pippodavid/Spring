package com.david.service.impl;

import com.david.dao.IAccountDao;
import com.david.dao.impl.AccountDaoImpl;
import com.david.dao.impl.AccountDaoImpl_1;
import com.david.domain.Account;
import com.david.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.sql.SQLException;
import java.util.List;

import static com.sun.corba.se.spi.activation.IIOP_CLEAR_TEXT.value;

/*
* 账户的业务层实现类
* 把下面xml的配置换成注解
* <bean id="accountService" class="com.david.service.impl.AccountServiceImpl"
* scope="singleton" init-method="init" destroy-method="destroy">
* <property name="" value="" || ref=""></property>
* </bean>
* 用于创建对象的注解
*     作用和xml文件中编写bean标签效果一样
*     @Component
*        作用：用于把当前类对象存入spring容器中
*        属性: value，用于指定bean的id，我们不写时，默认是当前类名且首字母改小写
*     @Controller:一般用在表现层
*     @Service:一般用在业务层
*     @Repository:一般用在持久层
*     以上三个和Component效果一样，分别对应psing框架的三层结构，使三层对象更加清晰
* 用于注入数据的注解
*     他们的作用就会在xml配置文件中的bean标签中的<property>标签作用是一样的
*     @Autowired:
*         作用：自动按照类型注入，只要容器中有唯一的一个bean对象和要注入的变量
*         类型匹配，就可以成功注入，如果IOC容器中没有匹配的bean类型就会报错，如果
*         IOC容器中有多个bean类型匹配，就需要配合Qualifier使用指定具体Bean类型
*         出现位置：可以在方法和成员变量上注入
*         在使用注解注入时,set方法就可以不用了。
*     @Qualifier
*         作用：在按照类中注入的基础之上再按名称注入。给类成员注入时不能单独使用，
*         但在给方法参入的注入时可以单独使用
*         属性：用于指定注入bean的ID
*      @Resource
*         作用：直接按照bean的ID注入，可以独立使用
*         属性：name用于指定bean的ID
*      以上三个注入都只能注入其他 bean类型的数据，而基本类型和String类型无法
*      使用上述注解实现。
*      另外，集合类型注入只能通过XML实现。
*
*      @Value
*         作用：用于注入基本类型和String类型的数据
*         属性：value：用于指定数据的值。它可以使用spring中SpEL(也就是spring的el表达式)
*            SpEL写法：${表达式}
*
* 用于改变作用范围的注解
*     他们的作用就和在bean标签中使用scope属性作用一样
*     @Scope
*        作用；用于指定Bean的作用范围
*        属性：value：指定范围的取值，常用取值：singleton,prototype(默认是singleton)
*
* 和生命周期相关的注解
*     作用和在bean标签中使用init-method和destroy-method属性作用是一样的
*     @PreDestory
*        用于指定销毁方法
*     @PostConstruct
*        用于指定初始化方法
* */
@Component
//@Scope("singleton")
public class AccountServiceImpl implements IAccountService {
    private IAccountDao accountDao;

    //@Autowired
    //@Qualifier("accountDao1")
    @Resource(name = "accountDao1")
    private AccountDaoImpl_1 accountDaoImpl_1;

    @Autowired
    @Qualifier("accountDao")
    private AccountDaoImpl accountDaoImpl;

    public AccountServiceImpl() {
        System.out.println("object is created");
    }

    public void init() {
        System.out.println("object has been initialized");
    }

    public void destroy() {
        System.out.println("objcet has been destoryed");
    }

    public void saveAccount(Account account) throws SQLException{
        System.out.println("saveAccount is running");
        accountDao.saveAccount(account);
    }

    public List<Account> findAllAccount() throws SQLException{
        return accountDao.findAllAccount();
    }

    public Account findAccountByID(Integer id) throws SQLException{
        return accountDao.findAccountByID(id);
    }

    public void updateAccount(Account account) throws SQLException{
        accountDao.updateAccount(account);
    }

    public void deleteAccount(Integer id) throws SQLException{
        accountDao.deleteAccount(id);
    }
}
