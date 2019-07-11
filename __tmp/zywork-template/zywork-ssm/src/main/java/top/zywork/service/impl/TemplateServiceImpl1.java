package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.dao.TemplateDAO1;
import top.zywork.dos.TemplateDO;
import top.zywork.dto.TemplateDTO;
import top.zywork.service.AbstractBaseService1;
import top.zywork.service.TemplateService1;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Template服务实现类<br />
 * 创建于2017-08-24
 *
 * @author 王振宇
 * @version 1.0
 */
@Service
public class TemplateServiceImpl1 extends AbstractBaseService1<Long> implements TemplateService1 {

    private TemplateDAO1 templateDAO;

    @PostConstruct
    public void initService() {
        super.initService(TemplateDO.class, TemplateDTO.class);
    }

    @Override
    public void save1(Object obj) {
        templateDAO.save1(obj);
    }

    @Autowired
    public void setTemplateDAO(TemplateDAO1 templateDAO) {
        super.setBaseDAO(templateDAO);
        this.templateDAO = templateDAO;
    }
}
