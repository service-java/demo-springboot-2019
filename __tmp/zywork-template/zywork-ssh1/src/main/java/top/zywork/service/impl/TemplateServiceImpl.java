package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.TemplateDAO;
import top.zywork.dos.TemplateDO;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.TemplateDTO;
import top.zywork.exception.DAOException;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.TemplateService;

import java.util.List;

/**
 * Template服务实现类<br />
 * 创建于2017-08-24
 *
 * @author 王振宇
 * @version 1.0
 */
@Service
public class TemplateServiceImpl extends AbstractBaseService implements TemplateService {

    private TemplateDAO templateDAO;

    @Override
    public void save(TemplateDTO templateDTO) {
        try {
            templateDAO.save(getDozerMapper().map(templateDTO, TemplateDO.class));
        } catch (DAOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public void remove(TemplateDTO templateDTO) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(TemplateDTO templateDTO) {

    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public TemplateDTO getById(Long id) {
        return null;
    }

    @Override
    public List<TemplateDTO> listAll() {
        return null;
    }

    @Override
    public PagerDTO<TemplateDTO> listPage(PageQuery pageQuery) {
        return null;
    }

    @Autowired
    public void setTemplateDAO(TemplateDAO templateDAO) {
        this.templateDAO = templateDAO;
    }
}
