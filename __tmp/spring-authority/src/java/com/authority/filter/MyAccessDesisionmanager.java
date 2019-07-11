package com.authority.filter;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;

import com.authority.model.SysUsers;

/**
 * @description 访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源 ;做最终的访问控制决定
 * @author tony
 */
public class MyAccessDesisionmanager implements AccessDecisionManager {

	private final static Logger logger = LoggerFactory
			.getLogger(MyAccessDesisionmanager.class);

	@Override
	public void decide(Authentication authentication, Object o,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		System.out.println(o.toString().startsWith("/public"));
		if (o.toString().startsWith("/public/")) {
			return;
		} else if (configAttributes == null) {
			return;
		} else {
			Iterator<ConfigAttribute> it = configAttributes.iterator();
			while (it.hasNext()) {
				ConfigAttribute configAttribute = it.next();
				if (authentication.getAuthorities().contains(configAttribute))
					return;
			}
		}
		logger.error("Access Deined");
		Object object = authentication.getPrincipal();

		if (object instanceof SysUsers) {
			SysUsers users = (SysUsers) object;
			logger.error(users.getUsername() + o.toString() + " access denied");
		}
		throw new AccessDeniedException("--------MyAccessDescisionManager：decide-------权限认证失败！");  
	}

	/** 
     * 启动时候被AbstractSecurityInterceptor调用，决定AccessDecisionManager是否可以执行传递ConfigAttribute。 
     */
	@Override
	public boolean supports(ConfigAttribute arg0) {
		System.out.println("MyAccessDescisionManager.supports()------------角色名："+arg0.getAttribute());  
		return true;
	}

	/** 
     * 被安全拦截器实现调用，包含安全拦截器将显示的AccessDecisionManager支持安全对象的类型 
     */  
	@Override
	public boolean supports(Class<?> arg0) {
		System.out.println("MyAccessDescisionManager.supports()--------------------------------");  
		return true;
	}

}
