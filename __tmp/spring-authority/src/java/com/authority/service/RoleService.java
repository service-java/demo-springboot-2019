package com.authority.service;

import com.authority.dao.IPager;
import com.authority.dao.impl.Pager;
import com.authority.model.SysRoles;
import com.authority.utils.ListResult;

public interface RoleService extends BaseService<SysRoles,String> {

    /**
     * 获取系统中所有的角色
     * @param pager
     * @return
     */
    public ListResult<SysRoles> getSysRoleList(IPager pager);

    /**
     * 添加角色
     * @param roles
     * @return
     */
    public Boolean addRole(SysRoles roles);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    public Boolean delRole(String roleId);

    /**
     * 获取角色权限
     * @return
     */
    public ListResult<SysRoles> getRoleAuthorities(Pager pager);


    /**
     * 添加角色权限
     * @param authIds
     * @param roldId
     * @return
     */
    public Boolean addRoleAuthority(String authIds,String roldId);

    /**
     * 删除角色权限
     * @param authIds
     * @param roleId
     * @return
     */
    public Boolean delRoleAuthority(String authIds,String roleId);

    /**
     *
      * @return
     */
    public ListResult<SysRoles> getSimpleRoleList();
}
