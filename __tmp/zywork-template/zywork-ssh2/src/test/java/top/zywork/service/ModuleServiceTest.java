package top.zywork.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 权限服务测试类<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 */
@ContextConfiguration(locations = {"classpath*:/config/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ModuleServiceTest {

    @Autowired
    private ModuleService moduleService;

    @Test
    public void testGetById() {
        System.out.println(moduleService.getById(1L).getTitle());
    }

}
