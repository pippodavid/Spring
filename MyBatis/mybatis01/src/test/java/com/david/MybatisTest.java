package com.david;

import com.david.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MybatisTest {

    private SqlSession sqlSession;
    private IUserDao dao;
    private InputStream is;
    @Autowired
    private SqlSessionTemplate sqlTemplate;

    @BeforeAll
    public void init() throws IOException{
        //1)读取配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2)创建SqlSessionFactory
        SqlSessionFactoryBuilder sqlBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlFactory = sqlBuilder.build(is);
        //3)使用工厂创建SqlSession对象
        sqlSession = sqlFactory.openSession();
        //4)使用SqlSession创建Dao接口的代理对象
        dao = sqlSession.getMapper(IUserDao.class);
    }

    @Test
    public void testFindAll() {
        //5)使用代理对象执行方法
        List<User> users = this.dao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testBatchInsert() {
        List<User> users = new ArrayList<User>();
        int userCount = 1000;
        User user = new User();
        for (int i = 0; i != userCount; i++) {
            user.setUsername("david");
            user.setAddress("shenzhen");
            user.setSex("m");
            Date date = new Date();
            date.setTime(100L);
            user.setBirthday(date);
            users.add(user);
        }
        dao.insertBatch(users);
    }

    @Test
    public void testBatchInsertByTemplate() {
        sqlSession = sqlTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        List<User> users = new ArrayList<User>();
        int userCount = 10000;
        User user = new User();
        try {
            int i = 0;
            for (; i != userCount; i++) {
                user.setUsername("david");
                user.setAddress("shenzhen");
                user.setSex("m");
                Date date = new Date();
                date.setTime(100L);
                user.setBirthday(date);
                users.add(user);
                dao.insertBatch(users);

                //每500条提交一次并做一次清缓存处理
                if (i % 500 == 0 || i == userCount - 1) {
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
        }
         catch (Exception e) {
            sqlSession.rollback();
        }
    }

    @AfterAll
    public void close() throws IOException{
        //这里需要提交事务
        sqlSession.commit();

        //6)释放资源
        sqlSession.close();
        is.close();
    }
}
