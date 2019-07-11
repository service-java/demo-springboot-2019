package com.authority.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class MyLoginFailHandler implements AuthenticationFailureHandler {


	@Override
	public void onAuthenticationFailure(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse,
			AuthenticationException authenticationexception)
			throws IOException, ServletException {
		System.out.println("µÇÂ¼Ê§°Ü");
	}
	
}
