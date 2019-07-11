package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.common.DozerMapperUtils;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.PermissionDAO;
import top.zywork.dos.PermissionDO;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.PermissionDTO;
import top.zywork.exception.DAOException;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.PermissionService;

import java.util.List;

/**
 * 权限Service实现类<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
@Service
public class PermissionServiceImpl extends AbstractBaseService implements PermissionService {

    private PermissionDAO permissionDAO;

    @Override
    public List<PermissionDTO> listByModuleId(String moduleId) {
        return null;
    }

    @Override
    public List<PermissionDTO> listByRoleId(String roleId) {
        return null;
    }

    @Override
    public List<PermissionDTO> listByRoleIds(List<Long> roleIds) {
        try {
            List<PermissionDO> permissionDOList = permissionDAO.listByRoleIds(roleIds);
            return DozerMapperUtils.map(getDozerMapper(), permissionDOList, PermissionDTO.class);
        } catch (DAOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public List<PermissionDTO> listByAccount(String account) {
        try {
            List<PermissionDO> permissionDOList = permissionDAO.listByAccount(account);
            return DozerMapperUtils.map(getDozerMapper(), permissionDOList, PermissionDTO.class);
        } catch (DAOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public void save(PermissionDTO permissionDTO) {

    }

    @Override
    public void remove(PermissionDTO permissionDTO) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(PermissionDTO permissionDTO) {

    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public PermissionDTO getById(Long id) {
        return null;
    }

    @Override
    public List<PermissionDTO> listAll() {
        return null;
    }

    @Override
    public PagerDTO<PermissionDTO> listPage(PageQuery pageQuery) {
        return null;
    }

    @Autowired
    public void setPermissionDAO(PermissionDAO permissionDAO) {
        this.permissionDAO = permissionDAO;
    }
}
