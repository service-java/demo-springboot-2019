package top.zywork.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.AppDaoSupport;
import top.zywork.dao.RoleDAO;
import top.zywork.dos.RoleDO;
import top.zywork.query.PageQuery;
import top.zywork.query.StatusQuery;

import java.util.List;

/**
 * 角色DAO实现类<br />
 * 创建于2017-09-11
 *
 * @author 王振宇
 * @version 1.0
 */
@Repository
public class RoleDAOImpl extends AppDaoSupport implements RoleDAO {
    @Override
    public List<RoleDO> listByAccount(String account) {
        return getHibernateTemplate().execute(new HibernateCallback<List<RoleDO>>() {
            @Override
            public List<RoleDO> doInHibernate(Session session) throws HibernateException {
                String hql = "select r from RoleDO r join r.userRoleDOSet ur join ur.userDO u"
                        + " where u.email = :account or u.phone = :account or u.accountName = :account";
                try {
                    Query<RoleDO> query = session.createQuery(hql, RoleDO.class);
                    query.setParameter("account", account);
                    return query.list();
                } catch (RuntimeException e) {
                    throw ExceptionUtils.daoException(e);
                }
            }
        });
    }

    @Override
    public void save(RoleDO roleDO) {

    }

    @Override
    public void remove(RoleDO roleDO) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(RoleDO roleDO) {

    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public RoleDO getById(Long id) {
        return null;
    }

    @Override
    public List<RoleDO> listAll() {
        return null;
    }

    @Override
    public List<RoleDO> listPage(PageQuery pageQuery) {
        return null;
    }

    @Override
    public Long count() {
        return 0L;
    }
}
