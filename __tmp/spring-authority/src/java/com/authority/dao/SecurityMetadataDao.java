/*
 * 
 */
package com.authority.dao;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;

public interface SecurityMetadataDao {

	public Map<String,Collection<ConfigAttribute>> getMetadata();

    
}
