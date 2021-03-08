
import com.david.service.IAccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 使用Junit单元测试：测试我们的配置
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "classpath:beanConfig.xml")
public class AccountServiceTest {

    @Autowired
    @Qualifier("proxyAccountService")
    private  IAccountService as;

    @Test
    public  void testTransfer(){
        as.transfer("aaa","bbb",100f);
    }

}
