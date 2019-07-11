package top.zywork.dao.impl;

import org.springframework.stereotype.Repository;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.AppDaoSupport;
import top.zywork.dao.TemplateDAO;
import top.zywork.dos.TemplateDO;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;

import java.util.List;

/**
 * TemplateDAO的实现类<br />
 * 创建于2017-08-24
 * @author 王振宇
 * @version 1.0
 */
@Repository
public class TemplateDAOImpl extends AppDaoSupport implements TemplateDAO {
    @Override
    public void save(TemplateDO templateDO) {
        try {
            getHibernateTemplate().save(templateDO);
        } catch (RuntimeException e) {
            throw ExceptionUtils.daoException(e);
        }
    }

    @Override
    public void remove(TemplateDO templateDO) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(TemplateDO templateDO) {

    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public TemplateDO getById(Long id) {
        return null;
    }

    @Override
    public List<TemplateDO> listAll() {
        return null;
    }

    @Override
    public List<TemplateDO> listPage(PageQuery pageQuery) {
        return null;
    }

    @Override
    public Long count() {
        return 0L;
    }
}
