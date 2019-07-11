package top.zywork.dao.impl;

import org.springframework.stereotype.Repository;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.AppDaoSupport;
import top.zywork.dao.ModuleDAO;
import top.zywork.dos.ModuleDO;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;

import java.util.List;

/**
 * 模块DAO实现类<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
@Repository
public class ModuleDAOImpl extends AppDaoSupport implements ModuleDAO {
    @Override
    public void save(ModuleDO moduleDO) {

    }

    @Override
    public void remove(ModuleDO moduleDO) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(ModuleDO moduleDO) {

    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public ModuleDO getById(Long id) {
        try {
            return getHibernateTemplate().get(ModuleDO.class, id);
        } catch (RuntimeException e) {
            throw ExceptionUtils.daoException(e);
        }
    }

    @Override
    public List<ModuleDO> listAll() {
        return null;
    }

    @Override
    public List<ModuleDO> listPage(PageQuery pageQuery) {
        return null;
    }

    @Override
    public Long count() {
        return 0L;
    }
}
