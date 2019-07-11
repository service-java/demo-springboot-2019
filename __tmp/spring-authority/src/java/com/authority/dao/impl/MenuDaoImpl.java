/*
 * 
 */
package com.authority.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.authority.dao.IPager;
import com.authority.dao.MenuDao;
import com.authority.model.SysMenus;
import com.authority.model.SysResources;
import com.authority.utils.ListResult;
import com.authority.utils.StringUtil;

@Repository("menuDao")
public class MenuDaoImpl extends BaseDaoImpl<SysMenus,String> implements MenuDao {
    private static final Logger logger = LoggerFactory.getLogger(MenuDaoImpl.class);

    @Override
    public ListResult<SysMenus> list(IPager pager) {
//        List menuList = getSession().createQuery("select * from SysMenus as m where m.sys=true and m.deep=1").list();
        List menuList = getSession().createQuery("select distinct " +
                "m.id as id," +
                "m.code as code," +
                "m.name as name," +
                "m.deep as deep," +
                "m.iconStyle as iconStyle," +
                "m.iconStyle as iconCls," +
                "m.menuIndex as menundex," +
                "m.url as url," +
                "m.sys as sys," +
                "m.menuIndex as menuIndex,"+
                "rescources.id as rescourcesId," +
                "rescources.resourcePath as resourcePath," +
                "m.children.size as childrenSize," +
                "case  when m.children.size>0 then 'closed' else 'open' end as state " +
                " from SysMenus m left join m.rescources as rescources where m.deep=1 and m.roleId is null order by menuIndex").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
        ListResult<SysMenus> result = new ListResult<SysMenus>();
        result.setCount(menuList.size());
        result.setDataList(menuList);
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListResult<SysMenus> listSon(IPager pager) {
        if(pager != null && pager.getParams()!=null && pager.getParams().get("parentid") != null){
           String parentid = (String) pager.getParams().get("parentid");
            List menuList = getSession().createQuery("select distinct " +
                    "m.id as id," +
                    "m.code as code," +
                    "m.name as name," +
                    "m.deep as deep," +
                    "m.iconStyle as iconStyle," +
                    "m.iconStyle as iconCls," +
                    "m.menuIndex as menuIndex," +
                    "m.url as url," +
                    "m.sys as sys," +
                    "rescources.id as resourceId," +
                    "rescources.resourcePath as resourcePath," +
                    "m.children.size as childrenSize," +
                    "case  when m.children.size>0 then 'closed' else 'open' end as state " +
                    " from SysMenus m left join m.rescources as rescources where m.parent.id=:parentid order by menuIndex").setString("parentid",parentid).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
            ListResult result = new ListResult();
            result.setCount(menuList.size());
            result.setDataList(menuList);
            return result;
        }
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean add(SysMenus menus) {
        SysResources resources = null;

        if(menus.getRescources() != null && StringUtil.isNotEmpty(menus.getRescources().getId())){
            resources = (SysResources) getSession().get(SysResources.class,menus.getRescources().getId());
            if(resources == null){
                return false;
            }
        }else{
            menus.setRescources(null);
        }
        if(StringUtil.isNotEmpty(menus.getId())){
            SysMenus menus1 = (SysMenus) getSession().get(SysMenus.class,menus.getId());
            if(menus1 != null){
               if(resources != null){
                   menus1.setRescources(resources);
               }
                menus1.setName(menus.getName());
                menus1.setSys(menus.isSys());
                menus1.setUrl(menus.getUrl());
                menus1.setIconStyle(menus.getIconStyle());
                menus1.setRescources(resources);
                getSession().update(menus1);
                return true;
            }
        }else {
            menus.setSys(true);
            if(menus.getParent() != null && StringUtil.isNotEmpty(menus.getParent().getId())){
                SysMenus SysMenus = (SysMenus) getSession().get(SysMenus.class,menus.getParent().getId());
                Long index = (Long) getSession().createQuery("select count(*) from SysMenus  m where m.parent.id=:pid").setString("pid",SysMenus.getId()).uniqueResult();
                if(index== null){
                    index=1l;
                }
                index = index+1;
                menus.setMenuIndex(index.intValue());
                if(SysMenus != null){
                    if(SysMenus.getChildren() == null){
                        SysMenus.setChildren(new ArrayList<SysMenus>());
                    }
                    SysMenus.getChildren().add(menus);
                    if(SysMenus.getDeep()>=3){
                        return false;
                    }
                    menus.setDeep(SysMenus.getDeep()+1);
                    getSession().update(SysMenus);
                }
            }else{
                Long index = (Long) getSession().createQuery("select count(*) from SysMenus  m where m.parent is null").uniqueResult();
                if(index== null){
                    index=1l;
                }
                index = index+1;
                menus.setMenuIndex(index.intValue());
                menus.setParent(null);
                menus.setDeep(1);
            }
            if(resources != null){
                menus.setRescources(resources);
            }
            getSession().save(menus);
            return true;
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean edit(SysMenus menus) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }





}
