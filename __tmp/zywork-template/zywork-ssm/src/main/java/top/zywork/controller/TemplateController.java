package top.zywork.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.RequestContext;
import top.zywork.common.EncryptUtils;
import top.zywork.common.ExceptionUtils;
import top.zywork.dto.TemplateDTO;
import top.zywork.exception.ServiceException;
import top.zywork.service.TemplateService;

import javax.servlet.http.HttpServletRequest;

/**
 * Template测试控制器类<br />
 * 创建于2017-08-24
 *
 * @author 王振宇
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/template")
public class TemplateController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(TemplateController.class);

    private TemplateService templateService;

    @RequestMapping(value = "ssm", method = RequestMethod.GET)
    public String add(HttpServletRequest request) {
        logger.info("template******");
        RequestContext requestContext = new RequestContext(request);
        logger.info(requestContext.getMessage("template.hello"));
        TemplateDTO templateDTO = new TemplateDTO();
        templateDTO.setName("test");
        //templateDTO.setPassword(EncryptUtils.md5("123456"));
        try {
            templateService.save(templateDTO);
        } catch (ServiceException e) {
            logger.error(ExceptionUtils.stackTraceString(new StringBuilder(""), e));
            throw e;
        }
        return "index";
    }

    @Autowired
    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }
}
