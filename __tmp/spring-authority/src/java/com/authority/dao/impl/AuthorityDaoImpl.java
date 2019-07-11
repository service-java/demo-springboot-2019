package com.authority.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.authority.dao.AuthorityDao;
import com.authority.dao.IPager;
import com.authority.model.SysAuthorities;
import com.authority.model.SysResources;
import com.authority.model.SysUsers;
import com.authority.utils.ListResult;
import com.authority.utils.StringUtil;

@Repository("authorityDao")
public class AuthorityDaoImpl extends BaseDaoImpl<SysAuthorities,String> implements AuthorityDao {
    @Override
    public ListResult<SysAuthorities> getAuthorityList(IPager pager) {
    	ListResult<SysAuthorities> listResult = null;
        String hql = "";
        SysUsers users = this.getUser();
        if(StringUtil.isNotEmpty(pager.getParams().get("authorityName"))){
            hql += " and m.authorityName like '%"+pager.getParams().get("authorityName")+"%'";
        }

        if(StringUtil.isNotEmpty(pager.getParams().get("authorityCode"))){
            hql += " and m.authorityCode like '%"+pager.getParams().get("authorityCode")+"%'";
        }

        if(users != null && users.isSys()){
//            if(StringUtil.isNotEmpty(hql.trim())){
//                hql = " where "+hql.replaceFirst("and","");
//            }
            if(StringUtil.isNotEmpty(pager.getOrder()) && StringUtil.isNotEmpty(pager.getSort())){
                hql += " order by m."+pager.getSort()+" "+pager.getOrder()+" ";
            }

            Long count = (Long) getSession().createQuery("select count(*) from SysAuthorities  m,SysUsers u where u.id = m.createUserId  "+hql).uniqueResult();
            if(count != null && count>0){
                listResult = new ListResult();
                List dataList = getSession().createQuery("select " +
                        "m.id as id," +
                        "m.authorityName as authorityName," +
                        "m.authorityCode as authorityCode," +
                        "m.authorityDesc as authorityDesc," +
                        "m.enabled as enabled," +
                        "u.userName as username," +
                        "DATE_FORMAT(m.createDate,'%Y-%m-%d') as createDate," +
                        "m.authority_resources.size as authorityRes" +
                        " from SysAuthorities m,SysUsers u where u.id = m.createUserId "+hql)
                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                        .setFirstResult(pager.getStart())
                        .setMaxResults(pager.getLimit())
                        .list();
                listResult.setCount(count);
                listResult.setDataList(dataList);
            }
        }else{
//            if(StringUtil.isNotEmpty(hql.trim())){
//                hql = " where "+hql.replaceFirst("and","");
//            }

            if(StringUtil.isNotEmpty(pager.getOrder()) && StringUtil.isNotEmpty(pager.getSort())){
                hql += " order by m."+pager.getSort()+" "+pager.getOrder()+" ";
            }
//            Long count = (Long) getSession().createQuery("select count(*) from SysAuthorities").uniqueResult();
            Long count = (Long)getSession().createQuery("select count(distinct a) from SysUsers u left join u.user_roles r left join r.role_authorities a where u.id=:id")
                            .setString("id",users.getId())
                            .uniqueResult();
            if(count != null && count>0){
                listResult = new ListResult();
                List dataList = getSession().createQuery("select distinct " +
                                    "m.id as id," +
                                    "m.authorityName as authorityName," +
                                    "m.authorityCode as authorityCode," +
                                    "m.authorityDesc as authorityDesc," +
                                    "m.enabled as enabled," +
                                    "u.userName as username," +
                                    "DATE_FORMAT(m.createDate,'%Y-%m-%d') as createDate," +
                                    "m.authority_resources.size as authorityRes" +
                                    " from SysUsers u left join u.user_roles r left join r.role_authorities m where u.id=:id "+hql)
                                    .setString("id",users.getId())
                                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                                    .setFirstResult(pager.getStart())
                                    .setMaxResults(pager.getLimit())
                                    .list();
                listResult.setCount(count);
                listResult.setDataList(dataList);
            }
        }
        return listResult;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean addAuthority(SysAuthorities authorities) {
        //ÐÞ¸Ä
        if(StringUtil.isNotEmpty(authorities.getId())){
                SysAuthorities SysAuthorities = (SysAuthorities)getSession().get(SysAuthorities.class,authorities.getId());
                if(SysAuthorities != null){
                    SysAuthorities.setAuthorityCode(authorities.getAuthorityCode());
                    SysAuthorities.setAuthorityDesc(authorities.getAuthorityDesc());
                    SysAuthorities.setEnabled(authorities.isEnabled());
                    SysAuthorities.setAuthorityName(authorities.getAuthorityName());
                    try{
                        getSession().update(SysAuthorities);
                        getSession().flush();
                        return true;
                    }catch (HibernateException e){
                        e.printStackTrace();
                    }
                 }
        //Ìí¼Ó
        }else{
            if(getUser() != null){
                try{
                    authorities.setCreateUserId(getUser().getId());
                    getSession().save(authorities);
                    getSession().flush();
                    return true;
                }catch (HibernateException e){
                    e.printStackTrace();
                }
             }
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean delAuthority(String authorityId) {
        try{
            Query query =getSession().createQuery("delete from SysAuthorities  where id=:id").setString("id",authorityId);
            Integer i = query.executeUpdate();
            if(i>0){
                return true;
            }
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListResult<SysAuthorities> getAuthorityRes(IPager pager) {
    	ListResult<SysAuthorities> resultList = null;
        String authorityId = (String) pager.getParams().get("authorityId");
        if(StringUtil.isNotEmpty(authorityId)){
            Integer count = (Integer) getSession().createQuery("select a.authority_resources.size from SysAuthorities a where a.id=:id")
                    .setString("id", authorityId)
                    .uniqueResult();
            if(count != null && count>0){
                resultList = new ListResult<SysAuthorities>();
                resultList.setCount(count);
                List dataList =getSession().createQuery("select " +
                        "resources.id as id," +
                        "resources.enabled as enabled," +
                        "resources.sys as isSys," +
                        "resources.resourceName as resourceName," +
                        "resources.resourcePath as resourcePath," +
                        "resources.resourceDesc as resourceDesc " +
                        "from SysAuthorities a left join a.authority_resources as resources where a.id=:id")
                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                        .setString("id",authorityId)
                        .setFirstResult(pager.getStart())
                        .setMaxResults(pager.getLimit())
                        .list();
                resultList.setDataList(dataList);
            }
        }



        return resultList;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean addAuthorityRes(String[] resIds,String authorityId) {

       Integer count = (Integer) getSession().createQuery("select r.authority_resources.size from SysAuthorities r where r.id=:authorityId").setString("authorityId",authorityId).uniqueResult();
        Query query = null;
        if(count != null && count==0){
            query = getSession().createQuery("from SysResources r where r.id in (:resIds)").setParameterList("resIds",resIds);
        }else{
            query = getSession().createQuery("from SysResources r where r.id in (:resIds) and r.id not in (" +
                    "select re.id from SysAuthorities au left join au.authority_resources re where au.id=:authorityId)").setParameterList("resIds",resIds).setString("authorityId",authorityId);
        }
        if(query != null){
            List list = query.list();
            SysAuthorities authorities = (SysAuthorities) getSession().get(SysAuthorities.class,authorityId);
            if(authorities != null && list.size()>0){
                if(authorities.getAuthority_resources() != null){
                    authorities.getAuthority_resources().addAll(list);


                }else{
                    authorities.setAuthority_resources(list);
                }
                try{
                    getSession().update(authorities);
                    getSession().flush();
                    return true;
                }catch (HibernateException e){
                    e.printStackTrace();
                }

            }
        }
        return false;
    }

    @Override
    public Boolean delAuthorityRes(String[] resIds,String authorityId) {
        List<SysResources> resList = getSession().createQuery("from SysResources r where r.id in (:resIds)").setParameterList("resIds",resIds).list();
        SysAuthorities authorities = (SysAuthorities) getSession().get(SysAuthorities.class,authorityId);

        if(authorities != null && resList.size()>0){
            for(SysResources resources:resList){
                resources.getResource_authorities().remove(authorities);
                authorities.getAuthority_resources().remove(resources);
                try{
                    getSession().update(authorities);
                    getSession().flush();
                    return true;
                }catch (HibernateException e){
                    e.printStackTrace();
                }
            }


        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

     @Override
     public Integer countAuthRole(String authId){
         Integer size = (Integer)getSession().createQuery("select a.authority_resources.size from SysAuthorities a where a.id=:authId").setString("authId",authId).uniqueResult();
         return size;
     }
}
