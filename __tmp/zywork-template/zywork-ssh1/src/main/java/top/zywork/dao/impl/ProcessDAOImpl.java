package top.zywork.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import top.zywork.common.ExceptionUtils;
import top.zywork.dao.AppDaoSupport;
import top.zywork.dao.ProcessDAO;
import top.zywork.dos.ProcessDO;
import top.zywork.query.PageQuery;
import top.zywork.query.ProcessDeployQuery;
import top.zywork.query.StatusQuery;

import java.util.List;

/**
 * 流程DAO实现类<br />
 * 创建于2017-10-14
 *
 * @author 王振宇
 * @version 1.0
 */
@Repository
public class ProcessDAOImpl extends AppDaoSupport implements ProcessDAO {
    @Override
    public void save(ProcessDO processDO) {
        try {
            getHibernateTemplate().save(processDO);
        } catch (RuntimeException e) {
            throw ExceptionUtils.daoException(e);
        }
    }

    @Override
    public void remove(ProcessDO processDO) {

    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public void update(ProcessDO processDO) {

    }

    @Override
    public void updateStatus(StatusQuery statusQuery) {

    }

    @Override
    public ProcessDO getById(Long id) {
        return null;
    }

    @Override
    public List<ProcessDO> listAll() {
        return null;
    }

    @Override
    public List<ProcessDO> listPage(PageQuery pageQuery) {
        return getHibernateTemplate().execute(new HibernateCallback<List<ProcessDO>>() {
            @Override
            public List<ProcessDO> doInHibernate(Session session) throws HibernateException {
                try {
                    Query<ProcessDO> query = session.createQuery("from ProcessDO");
                    query.setFirstResult(pageQuery.getBeginIndex());
                    query.setMaxResults(pageQuery.getPageSize());
                    return query.list();
                } catch (RuntimeException e) {
                    throw ExceptionUtils.daoException(e);
                }
            }
        });
    }

    @Override
    public Long count() {
        return getHibernateTemplate().execute(new HibernateCallback<Long>() {
            @Override
            public Long doInHibernate(Session session) throws HibernateException {
                Query<Long> query = session.createQuery("select count(id) from ProcessDO");
                return query.uniqueResult();
            }
        });
    }

    @Override
    public ProcessDO getByName(String name) {
        return getHibernateTemplate().execute(new HibernateCallback<ProcessDO>() {
            @Override
            public ProcessDO doInHibernate(Session session) throws HibernateException {
                Query<ProcessDO> query = session.createQuery("from ProcessDO where name = :name");
                query.setParameter("name", name);
                return query.uniqueResult();
            }
        });
    }

    @Override
    public void updateDeploy(ProcessDeployQuery processDeployQuery) {
        getHibernateTemplate().execute(new HibernateCallback<Integer>() {
            @Override
            public Integer doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("update ProcessDO set isDeploy = :isDeploy, deployTime = :deployTime where id = :id");
                query.setParameter("isDeploy", processDeployQuery.getIsDeploy());
                query.setParameter("deployTime", processDeployQuery.getDeployTime());
                query.setParameter("id", processDeployQuery.getProcessId());
                return query.executeUpdate();
            }
        });
    }
}
