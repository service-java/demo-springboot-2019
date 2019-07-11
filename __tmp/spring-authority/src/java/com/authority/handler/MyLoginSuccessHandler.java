package com.authority.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.authority.model.SysUsers;
import com.authority.model.UserLoginLog;
import com.authority.service.UserInfoService;

public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	@Qualifier("userInfoService")
	private UserInfoService userInfoService;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
		SysUsers users = null;
		Object user = SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		if (user != null && user instanceof SysUsers) {
			users = (SysUsers) user;
			httpServletResponse.sendRedirect("home");
			UserLoginLog log = new UserLoginLog();
			log.setLoginIp(httpServletRequest.getRemoteAddr());
			log.setLoginPort(httpServletRequest.getRemotePort());
			log.setLoginLocalPort(httpServletRequest.getLocalPort());
			log.setLoginTime(new Date());
			log.setUserId(users.getId());
			log.setUserName(users.getUserName());
			log.setUserAgent(httpServletRequest.getHeader("User-Agent"));
			String logid = userInfoService.addLoginLog(log);
			HttpSession session = httpServletRequest.getSession();
			if (logid != null && session != null) {
				session.setAttribute("USER_LOGIN_SUCCESS_ID", logid);
			}
		}
	}

}
