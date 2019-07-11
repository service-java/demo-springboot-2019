package com.authority.service;

import com.authority.dao.IPager;
import com.authority.dao.impl.Pager;
import com.authority.model.SysRoles;
import com.authority.utils.ListResult;

public interface RoleService extends BaseService<SysRoles,String> {

    /**
     * ��ȡϵͳ�����еĽ�ɫ
     * @param pager
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
     * @param roldId
     * @return
     */
    public Boolean addRoleAuthority(String authIds,String roldId);

    /**
     * ɾ����ɫȨ��
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
