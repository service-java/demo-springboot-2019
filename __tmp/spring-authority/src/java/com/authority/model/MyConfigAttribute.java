package com.authority.model;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;

public class MyConfigAttribute implements ConfigAttribute {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** The attribute. */
	private String attribute;
    @Override
    public String getAttribute() {
        return this.attribute;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(!(obj instanceof GrantedAuthority))return false;
        GrantedAuthority m = (GrantedAuthority) obj;
        if(m.getAuthority().equals(this.getAttribute()))return true;

        if(obj instanceof ConfigAttribute){
            ConfigAttribute configAttribute = (ConfigAttribute) obj;
            if(configAttribute.getAttribute().equals(this.getAttribute()))return true;
        }
        return false;
    }
    
}
