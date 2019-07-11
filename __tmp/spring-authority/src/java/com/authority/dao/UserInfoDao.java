package com.authority.dao;

import com.authority.model.SysUsers;
import com.authority.model.UserLoginLog;

public interface UserInfoDao {

	public SysUsers getUserByName(String username);

	public String addLoginLog(UserLoginLog log);

	public void addLogoutLog(String logid);

}
