import com.config.SpringConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试queryrunner是否单例
 */
public class QueryRunnerTest {

    @Autowired
    private QueryRunner runner;

    @Test
    public  void  testQueryRunner(){
        //1.获取容易
        ApplicationContext ac = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        //2.获取queryRunner对象
        //QueryRunner runner = ac.getBean("runner",QueryRunner.class);
        QueryRunner runner1 = ac.getBean("runner",QueryRunner.class);
        System.out.println(runner == runner1);
    }
}
