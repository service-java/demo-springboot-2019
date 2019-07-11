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
 * @description 资源源数据定义，将所有的资源和权限对应关系建立起来，即定义某一资源可以被哪些角色访问
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
		System.out.println("MySecurityMetadataSource.loadResourcesDefine()--------------开始加载资源列表数据--------");
		String url = ((FilterInvocation) o).getRequest().getServletPath();
		/* 保存资源和权限的对应关系 key-资源url value-权限 */
		Map<String, Collection<ConfigAttribute>> map = securitMetadataSourceDao
				.getMetadata();
		return map.get(url.trim());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
