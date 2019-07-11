package com.authority.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.authority.dao.RoleMenuDao;
import com.authority.model.SysMenus;
import com.authority.model.SysRoleMenus;
import com.authority.model.SysRoles;
import com.authority.model.SysUsers;
import com.authority.utils.ListResult;
import com.authority.utils.StringUtil;

@Repository("roleMenuDao")
public class RoleMenuDaoImpl extends BaseDaoImpl<SysRoleMenus,String> implements RoleMenuDao {



	    private void createMenu(Collection<SysMenus> menusList,SysRoles roles,Collection<String> ids){
	        Set<SysMenus>  menusSet = new HashSet<SysMenus>();
	        menusSet.addAll(menusList);
	        Iterator<SysMenus> it = menusSet.iterator();
	        int  i=1;
	        while (it.hasNext()){
	            SysMenus menus = it.next();
	            SysRoleMenus roleMenus = new SysRoleMenus();
	            roleMenus.setRole(roles);
	            roleMenus.setMenu(menus);
	            roleMenus.setInd(menus.getMenuIndex());
	            roleMenus.setEnable(false);
	            Collection<SysMenus> li = getSession().createQuery("select distinct m from SysMenus m left join m.children ms where (m.deep=:deep and m.rescources is null and ms.rescources.id in (:ids) and m.parent.id=:pid) or (m.deep=:deep and m.rescources is not null and m.rescources.id in (:ids) and m.parent.id=:pid) order by m.menuIndex")
	                    .setParameterList("ids",ids)
	                    .setInteger("deep",menus.getDeep()+1)
	                    .setString("pid",menus.getId())
	                    .list();
	            if(li != null && li.size()>0){
	                this.createMenu(li,roles,ids);
	            }
	            save(roleMenus);
	            i++;
	        }
	        return;
	    }

	    @Override
	    public ListResult<SysRoleMenus> loadRoleMenu(String roleId, String parentId) {
	    	ListResult<SysRoleMenus> result = new ListResult<SysRoleMenus>();
	        SysRoleMenus roleMenus = (SysRoleMenus) getSession().get(SysRoleMenus.class,parentId);
	        if(roleMenus != null){
	            result.setDataList(getSession().createQuery("select distinct " +
	                    "rm.id as id," +
	                    "m.iconStyle as iconCls," +
	                    "m.name as name," +
	                    "r.resourcePath as url," +
	                    "rm.ind as index " +
	                    ",case when (select count(*) from SysRoleMenus rms left join rms.menu ms where rm.role.id=:roleId and ms.parent.id=m.id)>0 then 'closed' else 'open' end  as state" +//判断是否有子节点
	                    " from SysRoleMenus rm left join rm.menu m left join m.rescources r where rm.role.id=:roleId and m.parent.id=:parentId order by rm.ind")
	                    .setString("roleId", roleId)
	                    .setCacheable(true)
	                    .setString("parentId", roleMenus.getMenu().getId())
	                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
	                    .list());
	        }
	        return result;
	    }

	    @Override
	    public ListResult<SysRoleMenus> loadRoleRootMenu(String roleId) {
	    	ListResult<SysRoleMenus> result = new ListResult<SysRoleMenus>();
//	        getSession().setCacheMode(CacheMode.REFRESH);
	        result.setDataList(getSession().createQuery("select distinct " +
	                "rm.id as id," +
	                "m.iconStyle as iconCls," +
	                "m.name as name," +
	                "rm.ind as index, " +
	                "r.resourcePath as url" +
	                ",case when (select count(*) from SysRoleMenus rms left join rms.menu ms where rm.role.id=:roleId and ms.parent.id=m.id)>0 then 'closed' else 'open' end  as state" +//判断是否有子节点
	                " from SysRoleMenus rm left join rm.menu m left join m.rescources r where rm.role.id=:roleId and m.deep=1 order by rm.ind")
	                .setString("roleId", roleId)
	                .setCacheable(true)
	                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
	                .list());
	        return result;
	    }
	    
		 @Override
		    public Boolean createRoleMenu(String roleId) {
		        getSession().createQuery("delete from SysRoleMenus where role.id=:roleId").setString("roleId",roleId).executeUpdate();
		        Collection<String> ids = getSession().createQuery("select rs.id from SysRoles r left join r.role_authorities ra left join ra.authority_resources as rs where r.id=:roleId").setString("roleId",roleId).list();
		        SysRoles roles = (SysRoles) getSession().get(SysRoles.class,roleId);
		        if(roles != null ){
		            List menus = getSession().createQuery("select distinct m from SysMenus m left join m.children ms where m.deep=1 and ((m.rescources.id in (:ids) and m.rescources is not null) or (ms.rescources.id in (:ids) and m.rescources is null)) order by m.menuIndex").setParameterList("ids",ids).list();
		            this.createMenu(menus,roles,ids);
		            if(menus.size()>0){
		                return true;
		            }
		        }
		        return false;  
		    }


	    @Override
	    public ListResult<SysRoleMenus> loadRoleRootMenu() {
	    	ListResult<SysRoleMenus> result = new ListResult<SysRoleMenus>();
	        Object obj = getUser();
	        if(obj != null && obj instanceof SysUsers){
	            SysUsers users = (SysUsers) obj;
	            Collection<SysRoles> roleses = users.getUser_roles();
	            List<String> ids = new ArrayList<String>();
	            for(SysRoles role:roleses){
	                ids.add(role.getId());
	            }
	            if(ids.size()>0){
	                result.setDataList(getSession().createQuery("select distinct " +
	                        "m.id as id," +
	                        "m.iconStyle as iconCls," +
	                        "m.name as text," +
	                        "rm.ind as index, " +
	                        "r.resourcePath as url" +
	                        ",case when (select count(*) from SysRoleMenus rms left join rms.menu ms where rm.role.id= rm.role.id and ms.parent.id=m.id)>0 then 'closed' else 'open' end  as state" +//判断是否有子节点
	                        " from SysRoleMenus rm left join rm.menu m left join m.rescources r where rm.role.id in (:roleId) and m.deep=1 order by rm.ind")
	                        .setParameterList("roleId", ids)
	                        .setCacheable(true)
	                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
	                        .list());
	                for(Object o:result.getDataList()){
	                    if(o instanceof Map){
	                        Map map = (Map) o;
	                        if(StringUtil.isNotEmpty(map.get("url"))){
	                            Map attribut = new HashMap();
	                            attribut.put("url",map.get("url"));
	                            map.put("attributes",attribut);
	                        }
	                    }
	                }
	            }
	        }
	        return result; 
	    }

	    @Override
	    public ListResult<SysRoleMenus> loadRoleMenu(String parentId) {

	    	ListResult<SysRoleMenus> result = new ListResult<SysRoleMenus>();
	        Object obj = getUser();
	        if(obj != null && obj instanceof SysUsers){
	            SysUsers users = (SysUsers) obj;
	            Collection<SysRoles> roleses = users.getUser_roles();
	            List<String> ids = new ArrayList<String>();
	            for(SysRoles role:roleses){
	                ids.add(role.getId());
	            }
	            if(ids.size()>0){
	                result.setDataList(getSession().createQuery("select distinct " +
	                        "m.id as id," +
	                        "m.iconStyle as iconCls," +
	                        "m.name as text," +
	                        "rm.ind as index, " +
	                        "r.resourcePath as url" +
	                        ",case when (select count(*) from SysRoleMenus rms left join rms.menu ms where rm.role.id= rm.role.id and ms.parent.id=m.id)>0 then 'closed' else 'open' end  as state" +//判断是否有子节点
	                        " from SysRoleMenus rm left join rm.menu m left join m.rescources r where rm.role.id in (:roleId)  and m.parent.id=:parentId order by rm.ind")
	                        .setParameterList("roleId", ids)
	                        .setString("parentId", parentId)
	                        .setCacheable(true)
	                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
	                        .list());
	                for(Object o:result.getDataList()){
	                    if(o instanceof Map){
	                        Map map = (Map) o;
	                        if(StringUtil.isNotEmpty(map.get("url"))){
	                            Map attribut = new HashMap();
	                            attribut.put("url",map.get("url"));
	                            map.put("attributes",attribut);
	                        }
	                    }
	                }
	            }
	        }
	        return result;
	    }

	    @Override
	    public Boolean moveUp(String roleid, String menuid) {
	        SysRoleMenus roleMenus = (SysRoleMenus) getSession().get(SysRoleMenus.class,menuid);
	        if(roleMenus != null && roleMenus.getInd()>1){
	            int index = roleMenus.getInd();
	            SysRoleMenus SysRoleMenus = null;
	            if(roleMenus.getMenu().getParent() != null){
	                SysRoleMenus = (SysRoleMenus) getSession().createQuery("from SysRoleMenus m where m.role.id=:roleid and m.menu.parent.id=:parentid and m.ind<:index order by m.ind desc ")
	                        .setInteger("index",index)
	                        .setString("roleid",roleid)
	                        .setMaxResults(1)
	                        .setString("parentid",roleMenus.getMenu().getParent().getId()).uniqueResult();
	            }else{
	                SysRoleMenus = (SysRoleMenus) getSession().createQuery("from SysRoleMenus m where m.role.id=:roleid and m.menu.parent is null and m.ind<:index order by m.ind desc ")
	                        .setInteger("index",index)
	                        .setString("roleid",roleid)
	                        .setMaxResults(1)
	                        .uniqueResult();
	            }

	            if(SysRoleMenus == null){
	                return false;
	            }
	            //交换两个菜单的顺序
	            roleMenus.setInd(SysRoleMenus.getInd());
	            SysRoleMenus.setInd(index);
	            this.update(roleMenus);
	            return true;
	        }
	        return false;  
	    }

	    @Override
	    public Boolean moveDown(String roleid, String menuid) {
	        SysRoleMenus roleMenus = (SysRoleMenus) getSession().get(SysRoleMenus.class,menuid);
	        if(roleMenus != null && roleMenus.getInd()>=1){
	            int index = roleMenus.getInd();
	            SysRoleMenus SysRoleMenus = null;

	            //如果不为根节点
	            if(roleMenus.getMenu().getParent() != null){
	                SysRoleMenus = (SysRoleMenus) getSession().createQuery("from SysRoleMenus m where m.role.id=:roleid and m.menu.parent.id=:parentid and m.ind>:index order by m.ind asc ")
	                        .setInteger("index",index)
	                        .setString("roleid",roleid)
	                        .setMaxResults(1)
	                        .setString("parentid",roleMenus.getMenu().getParent().getId()).uniqueResult();
	            }else{
	                SysRoleMenus = (SysRoleMenus)getSession().createQuery("from SysRoleMenus m where m.role.id=:roleid and m.menu.parent is null and m.ind>:index order by m.ind asc ")
	                        .setInteger("index",index)
	                        .setString("roleid",roleid)
	                        .setMaxResults(1)
	                        .uniqueResult();
	            }

	            if(SysRoleMenus == null){
	                return false;
	            }
	            //交换两个节点的顺序
	            roleMenus.setInd(SysRoleMenus.getInd());
	            SysRoleMenus.setInd(index);
	            this.update(roleMenus);
	            return true;
	        }
	        return false;  //To change body of implemented methods use File | Settings | File Templates.
	    }

}
