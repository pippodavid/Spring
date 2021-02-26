import david.Account;
import david.User;
import david.dao.IAccountDao;
import david.dao.IUserDao;
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
public class AccountTest {
    private IAccountDao dao;
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
        dao = sqlSession.getMapper(IAccountDao.class);
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
        List<Account> accounts = dao.findAll();
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println(account.getUser());
        }
    }
}
