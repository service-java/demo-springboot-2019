/*
 * 
 */
package com.authority.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;


// TODO: Auto-generated Javadoc
/**
 * The Class LogoutSuccessHandler.
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
	    
    	/* (non-Javadoc)
	     * @see org.springframework.security.web.authentication.logout.LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	     */
	    @Override
	    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

	    	HttpSession session = httpServletRequest.getSession();
    		session.removeAttribute("USER_LOGIN_SUCCESS_ID");
	        httpServletResponse.sendRedirect("");
	    }
	}