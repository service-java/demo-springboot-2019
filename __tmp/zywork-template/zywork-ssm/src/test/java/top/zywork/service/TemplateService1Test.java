package top.zywork.service;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import top.zywork.common.EncryptUtils;
import top.zywork.dto.TemplateDTO;

import java.util.List;

/**
 * Template服务测试类<br />
 * 创建于2017-08-24
 *
 * @author 王振宇
 */
@ContextConfiguration(locations = {"classpath*:/config/spring-*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TemplateService1Test {

    @Autowired
    private TemplateService1 templateService;

    @Test
    public void testSave1() {
        TemplateDTO templateDTO = new TemplateDTO();
        templateDTO.setName("test");
        templateDTO.setPassword(EncryptUtils.md5("123456"));
        templateService.save1(templateDTO);
    }

    @Test
    public void testGetById() {
        Object obj = templateService.getById(1L);
        TemplateDTO templateDTO = (TemplateDTO) obj;
        System.out.println(templateDTO.getName());
        System.out.println(JSON.toJSONString(obj));
    }

    @Test
    public void testListAll() {
        List<Object> list = templateService.listAll();
        System.out.println(JSON.toJSONString(list));
    }
}
