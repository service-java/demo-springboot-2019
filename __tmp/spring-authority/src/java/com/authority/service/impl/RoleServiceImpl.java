package com.authority.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.authority.dao.BaseDao;
import com.authority.dao.IPager;
import com.authority.dao.RoleDao;
import com.authority.dao.impl.Pager;
import com.authority.model.SysRoles;
import com.authority.service.RoleService;
import com.authority.utils.ListResult;
import com.authority.utils.StringUtil;

@Service("roleSerivce")
public class RoleServiceImpl extends BaseSerivceImpl<SysRoles,String> implements RoleService {

    private RoleDao roleDao;

    @Override
    public ListResult<SysRoles> getSysRoleList(IPager pager) {
        return roleDao.getSysRoleList(pager);
    }

    @Override
    public Boolean addRole(SysRoles roles) {
        if(!StringUtil.isNotEmpty(roles.getId())){
            roles.setCreateDate(new Date());
        }
        return roleDao.addRole(roles);
    }

    @Override
    public Boolean delRole(String roleId) {
        return roleDao.delRole(roleId);
    }

    @Override
    public  ListResult<SysRoles> getRoleAuthorities(Pager pager) {
        return roleDao.getRoleAuthorities(pager);
    }

    @Override
    public Boolean addRoleAuthority(String authIds, String roldId) {
        String[] authids = authIds.split(",");
        if(StringUtil.isNotEmpty(authids)){
           return roleDao.addRoleAuthority(authids,roldId);
        }
        return false;
    }

    @Override
    public Boolean delRoleAuthority(String authIds, String roleId) {
        String[] authids = authIds.split(",");
        if(StringUtil.isNotEmpty(authids)){
            return roleDao.delRoleAuthority(authids,roleId);
        }
        return false;
    }

    @Override
    public ListResult<SysRoles> getSimpleRoleList() {
        return roleDao.getSimpleRoleList();
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Autowired(required = true)
    @Qualifier("roleDao")
    @Override
    public void setBaseDao(BaseDao<SysRoles, String> baseDao) {
        this.baseDao = baseDao;
        this.roleDao = (RoleDao) baseDao;
    }
}
