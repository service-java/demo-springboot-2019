package com.authority.service;

import java.util.List;

import com.authority.dao.BaseDao;
import com.authority.dao.IPager;
import com.authority.dao.impl.Pager;
import com.authority.model.SysUsers;
import com.authority.utils.ListResult;

public interface UserService extends BaseDao<SysUsers,String>{
    public ListResult getUserData(IPager pager);

    public Boolean addUser(SysUsers users);

    public Boolean delUserById(String userid);

    public Boolean addUserRole(String roleIds,String userid);
    public Boolean delUserRole(String roleIds,String userid);


    public ListResult getUserRole(IPager pager);


    public Boolean saveUserPassword(String oldpassword,String newpassword);

    public Boolean updateEnableUser(String userid);

    public Boolean updateLockUser(String userid);

    public List getUserByType(String type);

    public boolean accountUniqueValidate(String userid,String account);

}