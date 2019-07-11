package com.authority.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.authority.dao.IPager;
import com.authority.dao.RoleDao;
import com.authority.model.SysAuthorities;
import com.authority.model.SysRoles;
import com.authority.model.SysUsers;
import com.authority.utils.ListResult;
import com.authority.utils.StringUtil;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<SysRoles,String> implements RoleDao {

    @Override
    public ListResult<SysRoles> getSysRoleList(IPager pager) {


        SysUsers users = getUser();
        String hql = "";
        if(users != null){
            hql = " and r.createUserId='"+users.getId()+"'";
        }
        if(StringUtil.isNotEmpty(pager.getOrder()) && StringUtil.isNotEmpty(pager.getSort())){
            hql += " order by r."+pager.getSort()+" "+pager.getOrder()+" ";
        }
        ListResult result = null;
        Long count = (Long) getSession().createQuery("select count(*) from SysRoles ").uniqueResult();
        if (count != null && count > 0) {
            result = new ListResult();
            List dataList = getSession().createQuery("select " +
                    "r.id as id," +
                    "r.roleName as roleName," +
                    "r.roleDesc as roleDesc," +
                    "r.enabled as enabled," +
                    "r.dataLevel as dateLevel," +
                    "DATE_FORMAT(r.createDate,'%Y-%m-%d') as createDate," +
                    "r.createUserId as createUserId," +
                    "r.role_authorities.size as authorityCount," +
                    "u.userName as createUserName" +
                    " from SysRoles r,SysUsers u  where u.id=r.createUserId "+hql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                    .setFirstResult(pager.getStart())
                    .setMaxResults(pager.getLimit()).list();
            result.setCount(count);
            result.setDataList(dataList);
        }
        return result;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean addRole(SysRoles roles) {
        //更新
        if (StringUtil.isNotEmpty(roles.getId())) {
            try {
                SysRoles SysRoles = (SysRoles) getSession().get(SysRoles.class, roles.getId());
                if (SysRoles != null) {
                    SysRoles.setRoleName(roles.getRoleName());
                    SysRoles.setRoleDesc(roles.getRoleDesc());
                    SysRoles.setDataLevel(roles.getDataLevel());
                    SysRoles.setEnabled(roles.isEnabled());
                    getSession().saveOrUpdate(SysRoles);
                    getSession().flush();
                    return true;
                }
            } catch (HibernateException e) {
                e.printStackTrace();
            }
            //新增
        } else {
            try {
                if (getUser() != null) {
                    roles.setCreateUserId(getUser().getId());
                    getSession().save(roles);
                    getSession().flush();
                    return true;
                }
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean delRole(String roleId) {
        if (StringUtil.isNotEmpty(roleId)) {
            SysRoles roles = (SysRoles) getSession().get(SysRoles.class, roleId);
            if (roles != null) {
                try {
                    getSession().delete(roles);
                    getSession().flush();
                    return true;
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListResult getRoleAuthorities(Pager pager) {
        ListResult listResult = null;
        if (pager.getParams() != null) {
            String roleId = (String) pager.getParams().get("roleId");
            if (roleId != null) {
                Integer count = (Integer) getSession().createQuery("select r.role_authorities.size from SysRoles r where r.id=:id").setString("id", roleId).uniqueResult();
                if (count != null && count > 0) {
                    listResult = new ListResult();
                    List dataList = getSession().createQuery("select " +
                            "role_authorities.id as id," +
                            "role_authorities.authorityName as authorityName," +
                            "role_authorities.authorityDesc as authorityDesc," +
                            "role_authorities.authorityCode as authorityCode," +
                            "DATE_FORMAT(role_authorities.createDate,'%Y-%m-%d') as createDate" +
                            " from SysRoles r left join r.role_authorities as role_authorities where r.id=:id").setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).setString("id", roleId)
                            .setMaxResults(pager.getLimit())
                            .setFirstResult(pager.getStart())
                            .list();
                    listResult.setDataList(dataList);
                    listResult.setCount(count);
                }
            }
        }
        return listResult;
    }

    @Override
    public Boolean addRoleAuthority(String[] authIds, String roleId) {
        Integer size = (Integer) getSession().createQuery("select r.role_authorities.size from SysRoles r where r.id=:roleId").setString("roleId", roleId).uniqueResult();
        List authList = null;
        if (size != null && size > 0) {
            authList = getSession().createQuery("from SysAuthorities au where au.id in (:authIds) and" +
                    " au.id not in (select rau.id from SysRoles r left join r.role_authorities rau where r.id=:roleId)")
                    .setParameterList("authIds", authIds)
                    .setString("roleId", roleId).list();

        } else {
            authList = getSession().createQuery("from SysAuthorities au where  au.id in (:authIds)").setParameterList("authIds", authIds).list();
        }
        if (authList != null && authList.size() > 0) {
            SysRoles roles = (SysRoles) getSession().get(SysRoles.class, roleId);
            if (roles != null) {
                if (roles.getRole_authorities() != null) {
                    roles.getRole_authorities().addAll(authList);
                } else {
                    roles.setRole_authorities(authList);
                }
                try {
                    getSession().update(roles);
                    getSession().flush();
                    return true;
                } catch (HibernateException e) {
                    e.printStackTrace();
                }
            }
        }


        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean delRoleAuthority(String[] authIds, String roleId) {
        List<SysAuthorities> authList = getSession().createQuery("from SysAuthorities  au where au.id in (:authIds)").setParameterList("authIds", authIds).list();
        if (authList != null && authList.size() > 0) {
            SysRoles roles = (SysRoles) getSession().get(SysRoles.class, roleId);
            if (roles != null) {
                for (SysAuthorities authorities : authList) {
                    roles.getRole_authorities().remove(authorities);
                    authorities.getAuthority_roles().remove(roles);
                    getSession().update(roles);
                    getSession().flush();
                }
                return true;
            }
        }
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @RequestMapping(value = "simpleList",method = RequestMethod.POST)
    @Override
    public ListResult getSimpleRoleList() {
        ListResult listResult = new ListResult();
        SysUsers users = this.getUser();
        if(users != null){
            listResult.setDataList(getSession().createQuery("select r.id as id,r.roleName as roleName from SysRoles r where r.createUserId=:id")
                    .setString("id", users.getId())
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list());
        }
        return listResult;
    }
}
