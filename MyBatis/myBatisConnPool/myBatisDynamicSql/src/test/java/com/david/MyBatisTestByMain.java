package com.david;

import com.david.dao.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyBatisTestByMain {
    private static InputStream in;
    private static SqlSession sqlSession;
    private static IUserDao dao;

    public static void main(String[] args) throws IOException {
        init();
        //testFindById(41);
        //testFindByName();
        //testFindByCondition();
        testFindByIds();
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
        //这个方法是设置autocommit属性的，这样不用每次执行commit()方法了
        //sqlSession = sqlSessionFactory.openSession(true);
        /*4.获取Dao代理对象
        * 这里实际调用的是的DefaultSqlSession的getMapper方法
        * 最终是在MapperProxyFacrory中newInstance方法创建代理(使用了动态代理技术)*/
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
        /*执行的时候最终也是通过sqlSession的selectList方法*/
        List<User> users = dao.findByName("%王%");
        if (users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    public static void testFindByCondition() {
        User user1 = new User();
        user1.setName("老王");
        List<User> users = dao.findByCondition(user1);
        if (users != null) {
            for (User u : users) {
                System.out.println(u);
            }
        }
    }

    public static void testFindByIds() {
        QueryVo vo = new QueryVo();
        List<Integer> ids = new ArrayList<Integer>();

        ids.add(41);
        ids.add(42);
        vo.setIds(ids);
        List<User> users = dao.findByIds(vo);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
