/*
 * 
 */
package com.authority.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Tony
 *
 */
@javax.persistence.Table(name = "sys_resources")
@Entity
public class SysResources implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The id. */
	private String id;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@javax.persistence.Column(name = "id")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Id
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/** The resource name. */
	private String resourceName;

	/**
	 * Gets the resource name.
	 *
	 * @return the resource name
	 */
	@javax.persistence.Column(name = "resource_name")
	@Basic
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * Sets the resource name.
	 *
	 * @param resourceName the new resource name
	 */
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/** The resource desc. */
	private String resourceDesc;

	/**
	 * Gets the resource desc.
	 *
	 * @return the resource desc
	 */
	@javax.persistence.Column(name = "resource_desc")
	@Basic
	public String getResourceDesc() {
		return resourceDesc;
	}

	/**
	 * Sets the resource desc.
	 *
	 * @param resourceDesc the new resource desc
	 */
	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	/** The resource type. */
	private Integer resourceType;

	/**
	 * Gets the resource type.
	 *
	 * @return the resource type
	 */
	@javax.persistence.Column(name = "resource_type")
	@Basic
	public Integer getResourceType() {
		return resourceType;
	}

	/**
	 * Sets the resource type.
	 *
	 * @param resourceType the new resource type
	 */
	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	/** The resource path. */
	private String resourcePath;

	/**
	 * Gets the resource path.
	 *
	 * @return the resource path
	 */
	@javax.persistence.Column(name = "resource_path")
	@Basic
	public String getResourcePath() {
		return resourcePath;
	}

	/**
	 * Sets the resource path.
	 *
	 * @param resourcePath the new resource path
	 */
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	/** The is enabled. */
	private Boolean isEnabled;

	/**
	 * Checks if is enabled.
	 *
	 * @return the boolean
	 */
	@javax.persistence.Column(name = "is_enabled")
	@Basic
	public Boolean isEnabled() {
		return isEnabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(Boolean enabled) {
		isEnabled = enabled;
	}

	/** The priority. */
	private String priority;

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	@javax.persistence.Column(name = "priority")
	@Basic
	public String getPriority() {
		return priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}

	/** The is sys. */
	private Boolean isSys;

	/**
	 * Checks if is sys.
	 *
	 * @return the boolean
	 */
	@javax.persistence.Column(name = "is_sys")
	@Basic
	public Boolean isSys() {
		return isSys;
	}

	/**
	 * Sets the sys.
	 *
	 * @param sys the new sys
	 */
	public void setSys(Boolean sys) {
		isSys = sys;
	}

	/** The resource_authorities. */
	private Collection<SysAuthorities> resource_authorities;

	/**
	 * Gets the resource_authorities.
	 *
	 * @return the resource_authorities
	 */
	@ManyToMany(mappedBy = "authority_resources", cascade = CascadeType.MERGE)
	@JsonIgnore
	public Collection<SysAuthorities> getResource_authorities() {
		return resource_authorities;
	}

	/**
	 * Sets the resource_authorities.
	 *
	 * @param resource_authorities the new resource_authorities
	 */
	public void setResource_authorities(
			Collection<SysAuthorities> resource_authorities) {
		this.resource_authorities = resource_authorities;
	}

	/** The parent. */
	private SysResources parent;

	/**
	 * Gets the parent.
	 *
	 * @return the parent
	 */
	@JsonIgnore
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	public SysResources getParent() {
		return parent;
	}

	/**
	 * Sets the parent.
	 *
	 * @param parent the new parent
	 */
	public void setParent(SysResources parent) {
		this.parent = parent;
	}

	/** The children. */
	private Collection<SysResources> children;

	

	/**
	 * Gets the children.
	 *
	 * @return the children
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER, cascade = {
			CascadeType.MERGE, CascadeType.PERSIST })
	public Collection<SysResources> getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 *
	 * @param children the new children
	 */
	public void setChildren(Collection<SysResources> children) {
		this.children = children;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		SysResources that = (SysResources) o;

		if (children != null ? !children.equals(that.children)
				: that.children != null)
			return false;
		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (isEnabled != null ? !isEnabled.equals(that.isEnabled)
				: that.isEnabled != null)
			return false;
		if (isSys != null ? !isSys.equals(that.isSys) : that.isSys != null)
			return false;
		if (parent != null ? !parent.equals(that.parent) : that.parent != null)
			return false;
		if (priority != null ? !priority.equals(that.priority)
				: that.priority != null)
			return false;
		if (resourceDesc != null ? !resourceDesc.equals(that.resourceDesc)
				: that.resourceDesc != null)
			return false;
		if (resourceName != null ? !resourceName.equals(that.resourceName)
				: that.resourceName != null)
			return false;
		if (resourcePath != null ? !resourcePath.equals(that.resourcePath)
				: that.resourcePath != null)
			return false;
		if (resourceType != null ? !resourceType.equals(that.resourceType)
				: that.resourceType != null)
			return false;
		if (resource_authorities != null ? !resource_authorities
				.equals(that.resource_authorities)
				: that.resource_authorities != null)
			return false;

		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result
				+ (resourceName != null ? resourceName.hashCode() : 0);
		result = 31 * result
				+ (resourceDesc != null ? resourceDesc.hashCode() : 0);
		result = 31 * result
				+ (resourceType != null ? resourceType.hashCode() : 0);
		result = 31 * result
				+ (resourcePath != null ? resourcePath.hashCode() : 0);
		result = 31 * result + (isEnabled != null ? isEnabled.hashCode() : 0);
		result = 31 * result + (priority != null ? priority.hashCode() : 0);
		result = 31 * result + (isSys != null ? isSys.hashCode() : 0);
		result = 31
				* result
				+ (resource_authorities != null ? resource_authorities
						.hashCode() : 0);
		result = 31 * result + (parent != null ? parent.hashCode() : 0);
		result = 31 * result + (children != null ? children.hashCode() : 0);
		return result;
	}

}
