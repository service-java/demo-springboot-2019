package com.authority.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.authority.model.UserLoginLog;

public interface UserInfoService  extends UserDetailsService{

	public String addLoginLog(UserLoginLog log);

	public void addLogoutLog(String logid);

}
