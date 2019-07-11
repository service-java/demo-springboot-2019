package com.authority.dao;

import com.authority.model.SysRoleMenus;
import com.authority.utils.ListResult;

public interface RoleMenuDao  extends BaseDao<SysRoleMenus,String>{

	 /**
     * ���ɽ�ɫ�˵�
     * @param roleId
     * @return
     */
    public Boolean createRoleMenu(String roleId);

    /**
     * ���ؽ�ɫ���˵�
     * @param roleId
     * @param parentId
     * @return
     */
    public ListResult<SysRoleMenus> loadRoleMenu(String roleId,String parentId);
    /**
     * ���ؽ�ɫ�˵�
     * @param roleId
     * @return
     */
    public ListResult<SysRoleMenus> loadRoleRootMenu(String roleId);

    /**
     * ���ؽ�ɫ���˵�
     * @return
     */
    public ListResult<SysRoleMenus> loadRoleRootMenu();

    /**
     * �����Ӳ˵�
     * @param parentId
     * @return
     */
    public ListResult<SysRoleMenus> loadRoleMenu(String parentId);
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
    
    
    
