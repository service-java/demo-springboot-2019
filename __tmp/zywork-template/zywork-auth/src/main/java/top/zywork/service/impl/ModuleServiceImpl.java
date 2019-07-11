package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.ModuleDAO;
import top.zywork.dto.ModuleDTO;
import top.zywork.dto.PagerDTO;
import top.zywork.exception.DAOException;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.ModuleService;

import java.util.List;

/**
 * 模块服务实现类<br />
 * 创建于2017-09-12
 *
 * @author 王振宇
 * @version 1.0
 */
@Service
public class ModuleServiceImpl extends AbstractBaseService implements ModuleService {

    private ModuleDAO moduleDAO;

    @Override
    public void save(ModuleDTO moduleDTO) {

    }

    @Override
    public void remove(ModuleDTO moduleDTO) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(ModuleDTO moduleDTO) {

    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public ModuleDTO getById(Long id) {
        try {
            return getDozerMapper().map(moduleDAO.getById(id), ModuleDTO.class);
        } catch (DAOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public List<ModuleDTO> listAll() {
        return null;
    }

    @Override
    public PagerDTO<ModuleDTO> listPage(PageQuery pageQuery) {
        return null;
    }

    @Autowired
    public void setModuleDAO(ModuleDAO moduleDAO) {
        this.moduleDAO = moduleDAO;
    }
}
