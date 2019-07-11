package top.zywork.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.zywork.common.DozerMapperUtils;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.RoleDAO;
import top.zywork.dos.RoleDO;
import top.zywork.dto.PagerDTO;
import top.zywork.dto.RoleDTO;
import top.zywork.exception.DAOException;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;
import top.zywork.service.AbstractBaseService;
import top.zywork.service.RoleService;

import java.util.List;

/**
 * 角色Service实现类<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
@Service
public class RoleServiceImpl extends AbstractBaseService implements RoleService {

    private RoleDAO roleDAO;

    @Override
    public List<RoleDTO> listByAccount(String account) {
        try {
            List<RoleDO> roleDOList = roleDAO.listByAccount(account);
            return DozerMapperUtils.map(getDozerMapper(), roleDOList, RoleDTO.class);
        } catch (DAOException e) {
            throw ExceptionUtils.serviceException(e);
        }
    }

    @Override
    public void save(RoleDTO roleDTO) {

    }

    @Override
    public void remove(RoleDTO roleDTO) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(RoleDTO roleDTO) {

    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public RoleDTO getById(Long id) {
        return null;
    }

    @Override
    public List<RoleDTO> listAll() {
        return null;
    }

    @Override
    public PagerDTO<RoleDTO> listPage(PageQuery pageQuery) {
        return null;
    }

    @Autowired
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }
}
