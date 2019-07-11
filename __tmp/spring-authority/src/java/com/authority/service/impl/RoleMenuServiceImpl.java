package com.authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.authority.dao.BaseDao;
import com.authority.dao.RoleMenuDao;
import com.authority.model.SysRoleMenus;
import com.authority.service.RoleMenuService;
import com.authority.utils.ListResult;
import com.authority.utils.StringUtil;

@Service("roleMenuService")
public class RoleMenuServiceImpl extends BaseSerivceImpl<SysRoleMenus,String> implements RoleMenuService {
    private RoleMenuDao menuDao;
    @Override
    public Boolean createRoleMenu(String roleId) {
        return menuDao.createRoleMenu(roleId);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ListResult loadRoleMenu(String roleId, String parentId) {
        if(StringUtil.isNotEmpty(parentId)){
            return menuDao.loadRoleMenu(roleId,parentId);
        }else{
            return menuDao.loadRoleRootMenu(roleId);
        }
    }

    @Override
    public ListResult loadRoleMenu(String id) {
        if(StringUtil.isNotEmpty(id)){
            return menuDao.loadRoleMenu(id);
        }else{
            return menuDao.loadRoleRootMenu();
        }
    }

    @Override
    public Boolean moveUp(String roleid, String menuid) {
        return menuDao.moveUp(roleid,menuid);
    }

    @Override
    public Boolean moveDown(String roleid, String menuid) {
        return menuDao.moveDown(roleid,menuid);
    }

    @Override
    @Autowired(required = true)
    @Qualifier("roleMenuDao")
    public void setBaseDao(BaseDao<SysRoleMenus, String> baseDao) {
        this.baseDao = baseDao;
        this.menuDao = (RoleMenuDao) baseDao;
    }


}
