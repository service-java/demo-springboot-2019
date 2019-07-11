package top.zywork.service.impl;

import org.dozer.Mapper;
import org.springframework.stereotype.Service;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.TemplateDAO;
import top.zywork.dos.TemplateDO;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.TemplateDTO;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;
import top.zywork.service.TemplateService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Template服务实现类<br />
 * 创建于2017-08-31
 *
 * @author 王振宇
 * @version 1.0
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private Mapper dozerMapper;
    private TemplateDAO templateDAO;

    @Override
    public void save(TemplateDTO templateDTO) {
        try {
            templateDAO.save(dozerMapper.map(templateDTO, TemplateDO.class));
        } catch (RuntimeException e) {
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
    public PagerDTO listPage(PageQuery pageQuery) {
        return null;
    }

    @Resource
    public void setDozerMapper(Mapper dozerMapper) {
        this.dozerMapper = dozerMapper;
    }

    @Resource
    public void setTemplateDAO(TemplateDAO templateDAO) {
        this.templateDAO = templateDAO;
    }
}
