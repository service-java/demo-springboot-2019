/*
 * 
 */
package com.authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.authority.dao.BaseDao;
import com.authority.dao.IPager;
import com.authority.dao.LoginLogDao;
import com.authority.model.UserLoginLog;
import com.authority.service.LoginLogService;
import com.authority.utils.ListResult;

@Service("loginLogService")
public class LoginLogServiceImpl extends BaseSerivceImpl<UserLoginLog,String> implements LoginLogService{
    private LoginLogDao loginLogDao;


    @Autowired(required = true)
    @Qualifier("loginLogDao")
    @Override
    public void setBaseDao(BaseDao<UserLoginLog, String> baseDao) {
        this.baseDao = baseDao;
        this.loginLogDao = (LoginLogDao) baseDao;
    }

    @Override
    public ListResult<UserLoginLog> list(IPager pager) {
        return loginLogDao.list(pager);
    }
}
