package com.authority.filter;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.authority.dao.SecurityMetadataDao;

/**
 * @description ��ԴԴ���ݶ��壬�����е���Դ��Ȩ�޶�Ӧ��ϵ����������������ĳһ��Դ���Ա���Щ��ɫ����
 * @author tony
 */
public class MySecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {

	@Autowired(required = true)
	@Qualifier("securityMetadataDao")
	private SecurityMetadataDao securitMetadataSourceDao;

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object o)
			throws IllegalArgumentException {
		System.out.println("MySecurityMetadataSource.loadResourcesDefine()--------------��ʼ������Դ�б�����--------");
		String url = ((FilterInvocation) o).getRequest().getServletPath();
		/* ������Դ��Ȩ�޵Ķ�Ӧ��ϵ key-��Դurl value-Ȩ�� */
		Map<String, Collection<ConfigAttribute>> map = securitMetadataSourceDao
				.getMetadata();
		return map.get(url.trim());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
