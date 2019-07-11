package com.authority.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;

import com.authority.dao.BaseDao;
import com.authority.model.SysUsers;
import com.authority.service.BaseService;

public abstract class BaseSerivceImpl<M extends Serializable,K extends Serializable> implements BaseService<M,K> {
	protected BaseDao<M,K> baseDao;
    private ArrayList<String> msg;

    public abstract void setBaseDao(BaseDao<M,K> baseDao);

    @Override
    public SysUsers getUser(){
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user != null && user instanceof SysUsers){
            return  (SysUsers) user;
        }
        return null;
    }

    @Override
    public K save(M model){
        return baseDao.save(model);
    }

    @Override
    public void saveOrUpdate(M model){
        baseDao.saveOrUpdate(model);
    }

    @Override
    public void update(M model){
        baseDao.update(model);
    }

    @Override
    public void delete(K id){
        baseDao.delete(id);
    }
    @Override
    public void deleteObject(M model){
        baseDao.deleteObject(model);
    }

    @Override
    public M get(K id){
        return baseDao.get(id);
    }

    @Override
    public List<String> getMsg() {
        return this.msg;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setMsg(String msg) {
        if(this.msg == null){
            this.msg = new ArrayList<String>();
        }
        this.msg.add(msg);
    }
}
