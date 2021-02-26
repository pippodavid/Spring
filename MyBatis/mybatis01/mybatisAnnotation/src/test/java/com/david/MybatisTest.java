package com.david;


import com.david.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
        //1)读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2)创建SqlSessionFactory
        SqlSessionFactoryBuilder sqlBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlFactory = sqlBuilder.build(is);
        //3)使用工厂创建SqlSession对象
        SqlSession sqlSession = sqlFactory.openSession();
        //4)使用SqlSession创建Dao接口的代理对象
        IUserDao dao = sqlSession.getMapper(IUserDao.class);
        //5)使用代理对象执行方法
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        //6)释放资源
        sqlSession.close();
        is.close();
    }
}
