package com.david;

import com.david.dao.IUserDao;
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
        //3.获取SqlSssion对象
        sqlSession = sqlSessionFactory.openSession();
        //4.获取Dao代理对象
        dao = sqlSession.getMapper(IUserDao.class);
    }

    //释放资源，用于在测试方法执行之后执行
    @AfterAll
    public void release() throws IOException{
        //这里需要提交事务
        sqlSession.commit();

        //释放资源
        if (sqlSession != null) {
            sqlSession.close();
        }
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

    /*由多个对象组成查询条件做查询，
    多个查询条件可以封装到自定义类中
    * 这里演示的还是通过名称查询*/
    @Test
    public void testFindByQryVo() {
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setName("%王%");
        List<User> users = dao.findByQryVo(vo);

        if (users != null) {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }
}
