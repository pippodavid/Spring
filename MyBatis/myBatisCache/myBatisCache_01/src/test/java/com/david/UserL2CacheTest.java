package com.david;

import com.david.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/*使用实例方法的单元测试
* 如果不加该注解而使用默认的静态方法的单元测试
* 那么afterall和beforeall都必须声明为静态方法*/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserL2CacheTest {
    private  InputStream in;
    private SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public void init() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    //释放资源，用于在测试方法执行之后执行
    @AfterAll
    public void release() throws IOException{
        if (in != null) {
            in.close();
        }
    }

    @Test
    /*
    * 使用同一个sqlSessionFactory
    * 但是sqlSession是重新获取的
    * 这样在sqlSessionFactory中的二级缓存是共用的*/
    public void testClearCache() {

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        IUserDao dao1 = sqlSession1.getMapper(IUserDao.class);
        User user1 = dao1.findById(41);
        System.out.println(user1);
        sqlSession1.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        IUserDao dao2 = sqlSession2.getMapper(IUserDao.class);
        User user2 = dao2.findById(41);
        System.out.println(user2);
        sqlSession2.close();

        //二级缓存存放的是数据，而不是对象，此处返回false，不是同一个对象
        System.out.println(user1.equals(user2));
        System.out.println(user1 == user2);
    }
}
