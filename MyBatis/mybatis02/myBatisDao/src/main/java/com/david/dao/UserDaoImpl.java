package com.david.dao;

import com.david.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.List;

/*
* mybatis支持自己写dao的实现类，不过这里会多一些实现代码要自己手写
* 比使用dao代理类的方法要繁琐一些
* */
public class UserDaoImpl implements IUserDao{
    private SqlSessionFactory factory;
    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    public List<User> findAll() {
        //获取SqlSssion对象
        SqlSession sqlSession = factory.openSession();
        /*参数就是获取配置信息的key
        * 跟踪源码这里执行的是DefaultSqlSession的selectList方法
        * 通过MyBatis的执行器Executor执行增删改查操作
        * 最终通过PreparedStatement的execute方法执行SQL*/

        List<User> users = sqlSession.selectList("com.david.dao.IUserDao.findAll");

        //释放资源
        sqlSession.close();
        return users;
    }

    public void saveUser(User user) {
        //根据factory获取SqlSssion对象
        SqlSession sqlSession = factory.openSession();
        //调用方法实现插入数据,这里需要传入user对象
        sqlSession.insert("com.david.dao.IUserDao.saveUser", user);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    public void updateUser(User user) {
        //获取SqlSssion对象
        SqlSession sqlSession = factory.openSession();
        //调用方法实现保存,这里需要传入user对象
        sqlSession.update("com.david.dao.IUserDao.updateUser", user);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    public void deleteUser(Integer userId) {
        //根据factory获取SqlSssion对象
        SqlSession sqlSession = factory.openSession();
        //调用方法实现保存,这里需要传入user对象
        sqlSession.delete("com.david.dao.IUserDao.deleteUser", userId);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
    }

    public User findById(Integer userId) {
        //根据factory获取SqlSssion对象
        SqlSession sqlSession = factory.openSession();
        //调用方法实现保存,这里需要传入user对象
        User user = sqlSession.selectOne("com.david.dao.IUserDao.findById", userId);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();

        return user;
    }

    public List<User> findByName(String name) {
        //根据factory获取SqlSssion对象
        SqlSession sqlSession = factory.openSession();
        //调用方法实现保存,这里需要传入user对象
        List<User> users = sqlSession.selectList("com.david.dao.IUserDao.saveUser", name);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        return users;
    }

    public int findTotal() {
        //根据factory获取SqlSssion对象
        SqlSession sqlSession = factory.openSession();
        //调用方法实现保存,这里需要传入user对象
        int count = sqlSession.selectOne("com.david.dao.IUserDao.findTotal");
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();
        return count;
    }
}
