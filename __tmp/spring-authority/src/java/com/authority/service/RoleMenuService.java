package com.authority.service;

import com.authority.model.SysRoleMenus;
import com.authority.utils.ListResult;

public interface RoleMenuService extends BaseService<SysRoleMenus,String> {

	/**
     * ���ɽ�ɫ�˵�
     * @param roleId
     * @return
     */
    public Boolean createRoleMenu(String roleId);

    /**
     * ���ؽ�ɫ�˵�
     * @param roleId
     * @param parentId
     * @return
     */
    public ListResult<SysRoleMenus> loadRoleMenu(String roleId,String parentId);
    /**
     * ���ؽ�ɫ�˵� ʹ��QUERY����
     * @return
     */
    public ListResult<SysRoleMenus> loadRoleMenu(String id);

    /**
     * �˵�����һλ
     * @param roleid
     * @param menuid
     * @return
     */
    public Boolean moveUp(String roleid,String menuid);

    /**
     * �˵�����һλ
     * @param roleid
     * @param menuid
     * @return
     */
    public Boolean moveDown(String roleid,String menuid);
    
}
