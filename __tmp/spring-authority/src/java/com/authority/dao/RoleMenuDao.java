package com.authority.dao;

import com.authority.model.SysRoleMenus;
import com.authority.utils.ListResult;

public interface RoleMenuDao  extends BaseDao<SysRoleMenus,String>{

	 /**
     * 生成角色菜单
     * @param roleId
     * @return
     */
    public Boolean createRoleMenu(String roleId);

    /**
     * 加载角色根菜单
     * @param roleId
     * @param parentId
     * @return
     */
    public ListResult<SysRoleMenus> loadRoleMenu(String roleId,String parentId);
    /**
     * 加载角色菜单
     * @param roleId
     * @return
     */
    public ListResult<SysRoleMenus> loadRoleRootMenu(String roleId);

    /**
     * 加载角色根菜单
     * @return
     */
    public ListResult<SysRoleMenus> loadRoleRootMenu();

    /**
     * 加载子菜单
     * @param parentId
     * @return
     */
    public ListResult<SysRoleMenus> loadRoleMenu(String parentId);
    /**
     * 菜单上移一位
     * @param roleid
     * @param menuid
     * @return
     */
    public Boolean moveUp(String roleid,String menuid);

    /**
     * 菜单下移一位
     * @param roleid
     * @param menuid
     * @return
     */
    public Boolean moveDown(String roleid,String menuid);
    
}
    
    
    
