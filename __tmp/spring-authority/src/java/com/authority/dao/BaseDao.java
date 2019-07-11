/*
 * 
 */
package com.authority.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<M extends Serializable,K extends Serializable> {
    public K save(M model);
    public void saveOrUpdate(M model);
    public void update(M model);
    public void deleteObject(M model);
    public void delete(K id);
    public M get(K id);
    public List<String> getMsg();
    public void setMsg(String msg);

}
