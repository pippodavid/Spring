import com.david.service.IAccountService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountTest {
    IAccountService as1;
    IAccountService as2;

    @BeforeAll
    public void initClient() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beanConfig.xml");
        as1 = (IAccountService)applicationContext.getBean("accountServiceImpl");
        as2 = (IAccountService)applicationContext.getBean("accountServiceImpl");
    }

    @Test
    public void testEquals() {
        System.out.println(as1 == as2);
    }

}
