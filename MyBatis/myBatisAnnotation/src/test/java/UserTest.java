import com.david.User;
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest {
    private IUserDao dao;
    private SqlSession sqlSession;
    private SqlSessionFactory sqlSessionFactory;
    private InputStream in;

    @BeforeAll
    public void init() throws IOException {
        //获取字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //根据字节输入流构建sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
        //根据sqlSessionFactory生产SqlSesssion
        sqlSession = sqlSessionFactory.openSession(true);
        //根据SqlSession获取Dao的代理对象
        dao = sqlSession.getMapper(IUserDao.class);
    }

    @AfterAll
    public void clear() throws IOException{
        //释放资源
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll()  {
        //执行Dao方法
        List<User> users = dao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testAdd() {
        User user = new User();
        user.setUserName("mybatis");
        user.setAddress("chaoyang, BeiJing");
        user.setSex("m");

        dao.addUser(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setUserName("update");
        user.setAddress("haidian, BeiJing");
        user.setSex("f");

        dao.updateUser(user);
    }

    @Test
    public void testDelete() {
        dao.deleteUser(49);
    }

    @Test
    public void testFindById() {
        User user = dao.findById(41);
        System.out.println(user);
    }

    @Test
    public void testFindByName() {
        List<User> users = dao.findByName("王");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
