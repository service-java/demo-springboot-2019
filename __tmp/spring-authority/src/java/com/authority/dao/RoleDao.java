package com.authority.dao;

import com.authority.dao.impl.Pager;
import com.authority.model.SysRoles;
import com.authority.utils.ListResult;

public interface RoleDao extends BaseDao<SysRoles,String> {
    /**
     * 获取系统中所有的角色
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
     * @param roleId
     * @return
     */
    public Boolean addRoleAuthority(String[] authIds,String roleId);

    /**
     * 删除角色权限
     * @param authIds
     * @param roleId
     * @return
     */
    public Boolean delRoleAuthority(String[] authIds,String roleId);


    /**
     *
     * @return
     */
    public ListResult<SysRoles> getSimpleRoleList();
}
