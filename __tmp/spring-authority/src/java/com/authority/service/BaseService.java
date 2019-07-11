package com.authority.service;

import java.io.Serializable;
import java.util.List;

import com.authority.model.SysUsers;

public interface BaseService<M extends Serializable,K extends Serializable> {
    public K save(M model);
    public void saveOrUpdate(M model);
    public void update(M model);
    public void delete(K id);
    public void deleteObject(M model);
    public M get(K id);
    public List<String> getMsg();
    public void setMsg(String msg);
    public SysUsers getUser();
}
