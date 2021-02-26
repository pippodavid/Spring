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
public class UserTest {
    private  InputStream in;
    private  SqlSession sqlSession;
    private  IUserDao dao;
    private SqlSessionFactory sqlSessionFactory;

    @BeforeAll
    public void init() throws IOException {
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSssion对象
        sqlSession = sqlSessionFactory.openSession();
        //这个方法是设置autocommit属性的，这样不用每次执行commit()方法了
        //sqlSession = sqlSessionFactory.openSession(true);
        /*4.获取Dao代理对象
        * 这里实际调用的是的DefaultSqlSession的getMapper方法
        * 最终是在MapperProxyFacrory中newInstance方法创建代理(使用了动态代理技术)*/
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
    public void testFindById(Integer id) {
        User user = dao.findById(id);
        if (user != null) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindAllUser() {
        List<User> users = dao.findAll();
        /*for (User user : users) {
            if (user != null) {
                System.out.println(user);
            }
        }*/
    }

    @Test
    public void testFirstLevelCache() {
        User user1 = dao.findById(41);
        System.out.println(user1);

        reConnSql();
        /*该方法也可以清空缓存，MyBatis的一级缓存
        指的是sqlSession对象的缓存
        二级缓存指的是sqlSessionFactory中的缓存
        */
        //sqlSession.clearCache();

        User user2 = dao.findById(41);
        System.out.println(user2);
        System.out.println(user1.equals(user2));
        System.out.println(user1 == user2);
    }

    @Test
    /*
    * myBatis的一级缓存，当调用SqlSession的
    * 修改，添加，删除，commit(),close()等方法，
    * 就会清空一级缓存*/
    public void testClearCache() {
        User user1 = dao.findById(41);
        System.out.println(user1);

        user1.setName("david");
        user1.setAddress("baoan,shenzhen");
        dao.updateUser(user1);

        User user2 = dao.findById(41);
        System.out.println(user2);
        System.out.println(user1.equals(user2));
        System.out.println(user1 == user2);
    }

    /*重新建连接，获取的对象是不同的
        * 当sqlSession对象消失时，一级缓存也消失了
        * 所以对象是不同的*/
    private void reConnSql() {
        sqlSession.close();
        sqlSession = sqlSessionFactory.openSession();
        dao = sqlSession.getMapper(IUserDao.class);
    }
}
