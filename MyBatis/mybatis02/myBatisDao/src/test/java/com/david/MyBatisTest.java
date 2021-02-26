package com.david;

import com.david.dao.IUserDao;
import com.david.dao.UserDaoImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;

public class MyBatisTest {
    private static Logger logger = Logger.getLogger(MyBatisTest.class);


    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao dao;

    //用于在测试方法执行之前执行
    @BeforeAll
    public void init() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂对象创建dao
        dao = new UserDaoImpl(sqlSessionFactory);
    }

    //释放资源，用于在测试方法执行之后执行
    @AfterAll
    public void release() throws IOException{
        if (in != null) {
            in.close();
        }
    }

    @Test
    public void testFindAll() throws IOException {
        //init();
        //执行查询语句
        List<User> users = dao.findAll();
        for(User user : users) {
            System.out.println(user);
        }
        //release();
    }

    @Test
    public void testSave() throws IOException {
        User user = new User();
        user.setUsername("david");
        user.setAddress("shenzhen");
        user.setSex("男");
        user.setBirthday(new Date());

        //执行插入语句
        dao.saveUser(user);

        //release();
    }

    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(41);
        user.setUsername("allen");
        user.setAddress("shenzhen");
        user.setSex("女");
        user.setBirthday(new Date());

        System.out.println("Before save is: " + user);
        dao.updateUser(user);
        System.out.println("After save is: " + user);
    }

    @Test
    public void testDeleteUser() {
        dao.deleteUser(41);
    }

    @Test
    public void testFindById() {
        User user = dao.findById(42);
        if (user != null) {
            System.out.println(user);
        }
    }

    /*映射到对应的sql语句是这个
        select * from user where username like #{name};
        这种方法实际执行的时候使用的是占位符，PreparedStatement类
    */
    @Test
    public void testFindByName() {
        List<User> users = dao.findByName("%王%");
        if (users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    /*模糊匹配查询符写在xml中
        select * from user where username like ${%value%};
        这样调用findByName的时候就不用传匹配符了
        这种方法实际执行的时候使用的Statement类的字符串拼接
    */
    @Test
    public void testFindByName02() {
        List<User> users = dao.findByName("王");
        if (users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    @Test
    public void testFindTotal() {
        int total = dao.findTotal();
        System.out.println(total);
    }
}
