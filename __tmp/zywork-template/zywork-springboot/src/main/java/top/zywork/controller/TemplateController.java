package top.zywork.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.zywork.common.DatePassUtils;
import top.zywork.common.EncryptUtils;
import top.zywork.common.ExceptionUtils;
import top.zywork.dto.TemplateDTO;
import top.zywork.exception.AppException;
import top.zywork.exception.ServiceException;
import top.zywork.service.TemplateService;

import javax.annotation.Resource;

/**
 * 模板控制器类<br />
 * 创建于2017-08-31
 * @author 王振宇
 * @version 1.0
 */
@RestController
@RequestMapping("/template")
public class TemplateController {

    private Logger logger = LoggerFactory.getLogger(TemplateController.class);
    private TemplateService templateService;

    @GetMapping("sb")
    public void add() {
        logger.info("template******");
        logger.debug("你好！");
        TemplateDTO templateDTO = new TemplateDTO();
        templateDTO.setName("test");
        templateDTO.setPassword(EncryptUtils.md5("123456"));
        try {
            templateService.save(templateDTO);
        } catch (ServiceException e) {
            logger.error(ExceptionUtils.stackTraceString(new StringBuilder(""), e));
            throw e;
        }
    }

    @Resource
    public void setTemplateService(TemplateService templateService) {
        this.templateService = templateService;
    }
}
