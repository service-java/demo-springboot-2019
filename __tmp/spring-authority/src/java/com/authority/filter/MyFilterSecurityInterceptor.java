package com.authority.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @description һ���Զ����filter��
 *              �������authenticationManager,accessDecisionManager,securityMetadataSource��������
 *              �� ���ǵ����п��ƽ�������������ʵ��
 * @author Tony
 */
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor
		implements Filter {

	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("------------MyFilterSecurityInterceptor.doFilter()-----------��ʼ��������....");

		FilterInvocation fi = new FilterInvocation(request, response, chain);
		InterceptorStatusToken token = super.beforeInvocation(fi);
		try {
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
		
		System.out.println("------------MyFilterSecurityInterceptor.doFilter()-----------�������÷���������...."); 
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		  return this.securityMetadataSource;
	}
	

    public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.securityMetadataSource = securityMetadataSource;
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return this.securityMetadataSource;
    }
    

}
