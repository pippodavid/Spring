package com.david;

import com.david.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class  MyBatisTestByMain {
    private static InputStream in;
    private static SqlSession sqlSession;
    private static IUserDao dao;

    public static void main(String[] args) throws IOException {
        init();
        testFindAll();
        //testFindByName();   //这里name没有映射出来，因为数据库使用的是userName，不匹配
        //释放资源
        release();
    }

    public static void init() throws IOException {
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
    public static void release() throws IOException{
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

    public static void testFindById(Integer id) {
        User user = dao.findById(id);
        if (user != null) {
            System.out.println(user);
        }
    }

    public static void testFindByName() {
        List<User> users = dao.findByName("%王%");
        if (users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    public static void testFindAll() throws IOException {
        List<User> users = dao.findAll();
        for(User user : users) {
            System.out.println(user);
        }
    }
}
