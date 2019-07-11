package com.authority.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Repository;
import com.authority.dao.SecurityMetadataDao;
import com.authority.model.MyConfigAttribute;
import com.authority.model.SysAuthorities;
import com.authority.model.SysResources;

@Scope(value = "prototype")
@Repository("securityMetadataDao")
public class SecurityMetadataDaoImpl implements SecurityMetadataDao {

	private static Map<String, Collection<ConfigAttribute>> local = null;
	private Session session = null;
	@Autowired(required = true)
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.authority.dao.SecurityMetadataDao#getMetadata()
	 */
	@Override
	public Map<String, Collection<ConfigAttribute>> getMetadata() {
		Map<String, Collection<ConfigAttribute>> configResource = local;
		if (configResource == null) {
			configResource = new HashMap<String, Collection<ConfigAttribute>>();
			List<SysResources> resourcesList = getSession().createQuery("from SysResources where enabled=true").list();
			Iterator<SysResources> it = resourcesList.iterator();
			while (it.hasNext()) {
				SysResources resources = it.next();
				Collection<SysAuthorities> anthoritiesCollection = resources
						.getResource_authorities();
				Iterator<SysAuthorities> its = anthoritiesCollection.iterator();
				Collection<ConfigAttribute> configList = new ArrayList<ConfigAttribute>();
				while (its.hasNext()) {
					SysAuthorities anthorities = its.next();
					MyConfigAttribute configAttribute = new MyConfigAttribute();
					configAttribute
							.setAttribute(anthorities.getAuthorityCode());
					configList.add(configAttribute);
					configResource.put(resources.getResourcePath(), configList);
				}
			}
			session.close();
			local = configResource;
		}

		return configResource; // To change body of implemented methods use File
								// | Settings | File Templates.
	}

	private Session getSession() {
		if (session == null || !session.isConnected() || !session.isOpen()) {
			if (session != null) {
				session.close();
			}
			session = sessionFactory.openSession();
		}
		return session;
	}
	
	
	    

}
