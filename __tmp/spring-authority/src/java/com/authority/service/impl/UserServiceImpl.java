/*
 * 
 */
package com.authority.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.authority.dao.BaseDao;
import com.authority.dao.IPager;
import com.authority.dao.UserDao;
import com.authority.model.SysUsers;
import com.authority.service.UserService;
import com.authority.utils.ListResult;
import com.authority.utils.StringUtil;

@Service("userService")
public class UserServiceImpl extends BaseSerivceImpl<SysUsers,String> implements UserService {
    private UserDao userManagerDao;
    @Override
    public ListResult getUserData(IPager pager) {
        return userManagerDao.getUserDataList(pager);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean addUser(SysUsers users) {
        if(!StringUtil.isNotEmpty(users.getId())){
            users.setCreateDate(new Date());
            users.setCreatorId(getUser().getId());
        }
        return userManagerDao.addUser(users);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean delUserById(String userid) {
        return userManagerDao.delUserById(userid);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean addUserRole(String roleIds, String userid) {
        String[] roleids = roleIds.split(",");
        if(StringUtil.isNotEmpty(roleids)){
            return userManagerDao.addUserRole(roleids,userid);
        }
        return false;
    }

    @Override
    public Boolean delUserRole(String roleIds, String userid) {
        String[] roleids = roleIds.split(",");
        if(StringUtil.isNotEmpty(roleids)){
            return userManagerDao.delUserRole(roleids,userid);
        }
        return false;
    }

    @Override
    public ListResult getUserRole(IPager pager) {
        return userManagerDao.getUserRole(pager);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean saveUserPassword(String oldpassword, String newpassword) {
        return userManagerDao.changePassworld(oldpassword,newpassword);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean updateEnableUser(String userid) {
        return userManagerDao.updateEnableUser(userid);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Boolean updateLockUser(String userid) {
        return userManagerDao.updateLockUser(userid);
    }

    @Override
    public List getUserByType(String type) {
        return userManagerDao.getUserByType(type);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean accountUniqueValidate(String userid, String account) {
        return userManagerDao.accountUniqueValidate(userid,account);  //To change body of implemented methods use File | Settings | File Templates.
    }


    @Autowired(required = true)
    @Qualifier("userDao")
    @Override
    public void setBaseDao(BaseDao<SysUsers, String> baseDao) {
        this.baseDao = baseDao;
        this.userManagerDao = (UserDao) baseDao;
    }
}
