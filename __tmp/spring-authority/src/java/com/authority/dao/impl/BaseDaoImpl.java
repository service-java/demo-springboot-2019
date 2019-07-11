package com.authority.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;

import com.authority.dao.BaseDao;
import com.authority.model.SysUsers;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseDaoImpl.
 *
 * @param <M> the generic type
 * @param <K> the key type
 */
public abstract class BaseDaoImpl<M extends Serializable,K extends Serializable> implements BaseDao<M,K> {
    
    /** The limit. */
    private Integer limit;
    
    /** The page. */
    private Integer page;
    
    /** The start. */
    private Integer start;
//    private MSysUsers user;
    /** The msg. */
private ArrayList<String> msg;

    /**
     * Gets the user.
     *
     * @return the user
     */
    public SysUsers getUser(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user != null && user instanceof SysUsers){
            return  (SysUsers) user;
        }
        return null;
    }

    /** The entity class. */
    private final Class<M> entityClass;
    
    /** The session factory. */
    @Autowired(required = true)
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Instantiates a new base dao impl.
     */
    protected BaseDaoImpl() {
        this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Gets the session.
     *
     * @return the session
     */
    public Session getSession(){
        Session session = null;
        try{
            session = sessionFactory.getCurrentSession();
        }catch (HibernateException e){
            e.printStackTrace();
            session =sessionFactory.openSession();
        }
        return session;
    }

    /**
     * Open session.
     *
     * @return the session
     */
    public Session openSession(){
        return sessionFactory.openSession();
    }

    /**
     * Save.
     *
     * @param model the model
     * @return the k
     */
    @Override
    public K save(M model){
        return (K)getSession().save(model);
    }

    /**
     * Save or update.
     *
     * @param model the model
     */
    @Override
    public void saveOrUpdate(M model){
        getSession().saveOrUpdate(model);
    }
    
    /**
     * Update.
     *
     * @param model the model
     */
    @Override
    public void update(M model){
        getSession().update(model);
    }

    /**
     * Delete object.
     *
     * @param model the model
     */
    @Override
    public void deleteObject(M model){
        getSession().delete(model);
    }
    
    /**
     * Delete.
     *
     * @param id the id
     */
    @Override
    public void delete(K id){
        getSession().delete(get(id));
    }

    /**
     * Gets the.
     *
     * @param id the id
     * @return the m
     */
    @Override
    public M get(K id){
        return (M)getSession().get(entityClass,id);
    }

    /**
     * Gets the limit.
     *
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets the limit.
     *
     * @param limit the new limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * Gets the page.
     *
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Sets the page.
     *
     * @param page the new page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * Gets the start.
     *
     * @return the start
     */
    public Integer getStart() {
        return limit*(page-1);
    }

    /**
     * Sets the start.
     *
     * @param start the new start
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * Gets the session factory.
     *
     * @return the session factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Sets the session factory.
     *
     * @param sessionFactory the new session factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Gets the msg.
     *
     * @return the msg
     */
    @Override
    public List<String> getMsg() {
        return this.msg;  //To change body of implemented methods use File | Settings | File Templates.
    }

    /**
     * Sets the msg.
     *
     * @param msg the new msg
     */
    @Override
    public void setMsg(String msg) {
        if(this.msg == null){
            this.msg = new ArrayList<String>();
        }
        this.msg.add(msg);
    }
}
