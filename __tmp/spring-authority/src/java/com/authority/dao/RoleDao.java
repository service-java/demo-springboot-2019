package com.authority.dao;

import com.authority.dao.impl.Pager;
import com.authority.model.SysRoles;
import com.authority.utils.ListResult;

public interface RoleDao extends BaseDao<SysRoles,String> {
    /**
     * ��ȡϵͳ�����еĽ�ɫ
     * @return
     */
    public ListResult<SysRoles> getSysRoleList(IPager pager);

    /**
     * ��ӽ�ɫ
     * @param roles
     * @return
     */
    public Boolean addRole(SysRoles roles);

    /**
     * ɾ����ɫ
     * @param roleId
     * @return
     */
    public Boolean delRole(String roleId);

    /**
     * ��ȡ��ɫȨ��
     * @return
     */
    public ListResult<SysRoles> getRoleAuthorities(Pager pager);

    /**
     * ��ӽ�ɫȨ��
     * @param authIds
     * @param roleId
     * @return
     */
    public Boolean addRoleAuthority(String[] authIds,String roleId);

    /**
     * ɾ����ɫȨ��
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
