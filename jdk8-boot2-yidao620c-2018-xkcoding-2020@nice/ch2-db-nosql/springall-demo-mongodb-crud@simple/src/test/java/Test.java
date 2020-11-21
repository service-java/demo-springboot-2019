import com.example.mongodb.dao.UserDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2020-11-05 21:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    private UserDao userDao;

    @Autowired
    private MongoTemplate template;

    @org.junit.Test
    public void test() {

    }
}
