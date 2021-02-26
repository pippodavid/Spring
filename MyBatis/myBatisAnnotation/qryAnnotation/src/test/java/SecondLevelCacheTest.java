import david.User;
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SecondLevelCacheTest {
    private SqlSessionFactory sqlSessionFactory;
    private InputStream in;

    @BeforeAll
    public void init() throws IOException {
        //获取字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //根据字节输入流构建sqlSessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }

    @AfterAll
    public void clear() throws IOException{
        //释放资源
        in.close();
    }
    @Test
    public void testFindById() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        IUserDao dao = sqlSession.getMapper(IUserDao.class);

        User user = dao.findById(43);
        System.out.println(user);

        sqlSession.close();   //释放一级缓存

        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        IUserDao dao1 = sqlSession.getMapper(IUserDao.class);

        User user1 = dao1.findById(43);
        System.out.println(user1);

        sqlSession1.close();   //释放一级缓存
    }
}
