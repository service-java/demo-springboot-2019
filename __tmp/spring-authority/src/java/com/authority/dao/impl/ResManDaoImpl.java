package com.authority.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.authority.dao.IPager;
import com.authority.dao.ResManDao;
import com.authority.model.SysResources;
import com.authority.model.SysUsers;
import com.authority.utils.ListResult;
import com.authority.utils.StringUtil;

@Repository("resoucesDao")
public class ResManDaoImpl extends BaseDaoImpl<SysResources,String> implements ResManDao {
	  @Override
	    public ListResult getGroupList(IPager pager) {
	        ListResult listResult = null;
	        String hql = "";
	        if(StringUtil.isNotEmpty(pager.getParams().get("resourceName"))){
	            hql += " and s.resourceName like '%"+pager.getParams().get("resourceName")+"%'";
	        }
	        SysUsers users = this.getUser();
	        if(users != null && users.isSys()){
	            if(StringUtil.isNotEmpty(pager.getOrder()) && StringUtil.isNotEmpty(pager.getSort())){
	                hql += " order by "+pager.getOrder()+" "+pager.getSort()+" ";
	            }
	            Long count = (Long) getSession().createQuery("select count(*) from SysResources s where s.parent is  null "+hql).uniqueResult();
	            if(count !=null && count>0){
	                listResult = new ListResult();
	                listResult.setCount(count);
	                listResult.setDataList(getSession().createQuery("from SysResources s  where s.parent is  null " + hql)
	                        .setFirstResult(pager.getStart())
	                        .setMaxResults(pager.getLimit())
	                        .list());
	            }
	        }else{
	            if(StringUtil.isNotEmpty(pager.getOrder()) && StringUtil.isNotEmpty(pager.getSort())){
	                hql += " order by s."+pager.getOrder()+" "+pager.getSort()+" ";
	            }

	            Long count = (Long) getSession().createQuery("select  count(distinct  s) " +
	                    "from SysUsers u left join u.user_roles r left join r.role_authorities a left join a.authority_resources s" +
	                    " where u.id=:id and s.parent is null "+hql)
	                    .setString("id",users.getId())
	                    .uniqueResult();
	            if(count != null && count>0){
	                listResult = new ListResult();
	                listResult.setCount(count);
	                
	                listResult.setDataList(getSession().createQuery("select s from SysUsers u left join u.user_roles r left join r.role_authorities a left join a.authority_resources s" +
	                        "   where u.id=:id and s.parent is null " + hql)
	                        .setString("id", users.getId())
	                        .setMaxResults(pager.getLimit())
	                        .setFirstResult(pager.getStart())
	                        .list());
	            }
	        }

	        return listResult;  //To change body of implemented methods use File | Settings | File Templates.
	    }
	  
	  
    @Override
    public Boolean addGroup(SysResources group) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean addRes(SysResources resources) {
        try{
            if(!StringUtil.isNotEmpty(resources.getId())){
                resources.setSys(false);
                if(StringUtil.isNotEmpty(resources.getParent().getId())){
                    SysResources SysResources = (SysResources)getSession().get(SysResources.class,resources.getParent().getId());
                    if(SysResources != null){
                        resources.setParent(SysResources);
                        if(SysResources.getChildren() == null){
                            SysResources.setChildren(new ArrayList<SysResources>());
                        }
                        SysResources.getChildren().add(resources);
                    }else {
                        return false;
                    }
                    getSession().save(resources);
                    resources.setChildren(null);
                    resources.setParent(null);
                    getSession().save(resources);
                }
                getSession().save(resources);
            }else{
                SysResources resources1 =(SysResources) getSession().get(SysResources.class,resources.getId());
                if(resources1 != null){
                    resources1.setResourceName(resources.getResourceName());
                    resources1.setResourceType(resources.getResourceType());
                    resources1.setResourceDesc(resources.getResourceDesc());
                    resources1.setResourcePath(resources.getResourcePath());
                    getSession().update(resources1);
                }else {
                    return false;
                }
            }
            getSession().flush();
            return true;
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean delGroup(String groupid) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean delRes(String resid) {
        Query q = getSession().createQuery("delete from SysResources r where r.id=:resid").setString("resid",resid);
        try{
            Integer i  = q.executeUpdate();
            if(i>0){
                return true;
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
