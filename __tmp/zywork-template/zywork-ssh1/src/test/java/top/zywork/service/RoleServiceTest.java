package top.zywork.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.zywork.dto.RoleDTO;

import java.util.List;

/**
 * 角色服务测试类<br />
 * 创建于2017-08-24
 *
 * @author 王振宇
 */
@ContextConfiguration(locations = {"classpath*:/config/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void testListByAccount() {
        List<RoleDTO> roleDTOList = roleService.listByAccount("13672297775");
        for (RoleDTO roleDTO : roleDTOList) {
            System.out.println(roleDTO.getTitle());
        }
    }

}
