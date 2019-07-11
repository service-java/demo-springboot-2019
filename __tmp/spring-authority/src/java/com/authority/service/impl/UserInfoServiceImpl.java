package com.authority.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.authority.dao.UserInfoDao;
import com.authority.model.SysUsers;
import com.authority.model.UserLoginLog;
import com.authority.service.UserInfoService;
import com.authority.utils.StringUtil;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired(required = true)
	@Qualifier("userInfoDao")
	private UserInfoDao userInfoDao;

	@Override
	@Transactional
	public String addLoginLog(UserLoginLog log) {
		 return userInfoDao.addLoginLog(log);
	}

	@Override
	@Transactional
	public void addLogoutLog(String logid) {
		userInfoDao.addLogoutLog(logid);
	}

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		if (StringUtil.isNotEmpty(arg0)) {
			SysUsers users = userInfoDao.getUserByName(arg0.trim());
			if (users != null) {
				return users;
			}
		}
		throw new UsernameNotFoundException(
				"Can't not find user while username is '" + arg0.trim() + "'");
	}

}
