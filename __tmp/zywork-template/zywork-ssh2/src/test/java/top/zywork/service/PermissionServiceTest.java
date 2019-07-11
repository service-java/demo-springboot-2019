package top.zywork.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.zywork.dto.PermissionDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限服务测试类<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 */
@ContextConfiguration(locations = {"classpath*:/config/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void testListByRoleIds() {
        List<Long> roleIds = new ArrayList<>();
        roleIds.add(1L);
        roleIds.add(2L);
        List<PermissionDTO> permissionDTOList = permissionService.listByRoleIds(roleIds);
        for (PermissionDTO permissionDTO : permissionDTOList) {
            System.out.println(permissionDTO.getTitle());
        }
    }

    @Test
    public void testListByAccount() {
        List<PermissionDTO> permissionDTOList = permissionService.listByAccount("13672297775");
        for (PermissionDTO permissionDTO : permissionDTOList) {
            System.out.println(permissionDTO.getTitle());
        }
    }

}
