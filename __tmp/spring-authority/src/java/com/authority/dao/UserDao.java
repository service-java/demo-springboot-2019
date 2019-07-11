package com.authority.dao;

import java.util.List;

import com.authority.dao.impl.Pager;
import com.authority.model.SysUsers;
import com.authority.utils.ListResult;

public interface UserDao extends BaseDao<SysUsers,String>{
    /**
     * 获取用户列表
     * @param pager
     * @return
     */
    public ListResult getUserDataList(IPager pager);

    /**
     * 添加用户
     * @param users
     * @return
     */
    public Boolean addUser(SysUsers users);

    /**
     * 删除用户
     * @param userid
     * @return
     */
    public Boolean delUserById(String userid);

    /**
     * 添加用户角色
     * @param roleIds
     * @param userid
     * @return
     */
    public Boolean addUserRole(String[] roleIds,String userid);

    /**
     * 删除用户角色
     * @param roleIds
     * @param userid
     * @return
     */
    public Boolean delUserRole(String[] roleIds,String userid);

    /**
     * 获取用户角色列表
     * @param pager
     * @return
     */
    public ListResult getUserRole(IPager pager);

    /**
     * 修改密码
     * @param oldpassword
     * @param newpassword
     * @return
     */
    public Boolean changePassworld(String oldpassword,String newpassword);

    /**
     * 禁用或启用用户账号
     * @param userid
     * @return
     */
    public Boolean updateEnableUser(String userid);

    /**
     * 锁定或解锁用户账号
     * @param userid
     * @return
     */
    public Boolean updateLockUser(String userid);


    /**
     * 根据用户类型获取用户
     * @param type
     * @return
     */
    public List getUserByType(String type);

    boolean accountUniqueValidate(String userid, String account);
}
