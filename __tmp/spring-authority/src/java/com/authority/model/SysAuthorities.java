package com.authority.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;

@javax.persistence.Table(name = "sys_authorities")
@Entity
public class SysAuthorities implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;

	@javax.persistence.Column(name = "id")
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String authorityName;

	@javax.persistence.Column(name = "authority_name")
	@Basic
	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	private String authorityDesc;

	@javax.persistence.Column(name = "authority_desc")
	@Basic
	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	private String authorityCode;

	@javax.persistence.Column(name = "authority_code", unique = true)
	@Basic
	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	private boolean isEnabled;

	@javax.persistence.Column(name = "is_enabled")
	@Basic
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	private Date createDate;

	@javax.persistence.Column(name = "create_date")
	@Basic
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	private String createUserId;

	@javax.persistence.Column(name = "create_user_id")
	@Basic
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	private boolean sys;

	@javax.persistence.Column(name = "is_sys")
	@Basic
	public boolean isSys() {
		return sys;
	}

	public void setSys(boolean sys) {
		this.sys = sys;
	}

	private Collection<SysRoles> authority_roles;

	@ManyToMany(mappedBy = "role_authorities", cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	public Collection<SysRoles> getAuthority_roles() {
		return authority_roles;
	}

	public void setAuthority_roles(Collection<SysRoles> authority_roles) {
		this.authority_roles = authority_roles;
	}

	private Collection<SysResources> authority_resources;

	@javax.persistence.JoinTable(name = "sys_authorities_resources", joinColumns = @javax.persistence.JoinColumn(name = "authority_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "resource_id", referencedColumnName = "id", nullable = false))
	@ManyToMany(cascade = CascadeType.MERGE)
	public Collection<SysResources> getAuthority_resources() {
		return authority_resources;
	}

	public void setAuthority_resources(
			Collection<SysResources> authority_resources) {
		this.authority_resources = authority_resources;
	}

	@Override
	@Transient
	public String getAuthority() {
		return this.authorityCode; // To change body of implemented methods use
									// File | Settings | File Templates.
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		SysAuthorities that = (SysAuthorities) o;

		if (isEnabled != that.isEnabled)
			return false;
		if (sys != that.sys)
			return false;
		if (authorityCode != null ? !authorityCode.equals(that.authorityCode)
				: that.authorityCode != null)
			return false;
		if (authorityDesc != null ? !authorityDesc.equals(that.authorityDesc)
				: that.authorityDesc != null)
			return false;
		if (authorityName != null ? !authorityName.equals(that.authorityName)
				: that.authorityName != null)
			return false;
		if (authority_resources != null ? !authority_resources
				.equals(that.authority_resources)
				: that.authority_resources != null)
			return false;
		if (createDate != null ? !createDate.equals(that.createDate)
				: that.createDate != null)
			return false;
		if (createUserId != null ? !createUserId.equals(that.createUserId)
				: that.createUserId != null)
			return false;
		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result
				+ (authorityName != null ? authorityName.hashCode() : 0);
		result = 31 * result
				+ (authorityDesc != null ? authorityDesc.hashCode() : 0);
		result = 31 * result
				+ (authorityCode != null ? authorityCode.hashCode() : 0);
		result = 31 * result + (isEnabled ? 1 : 0);
		result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
		result = 31 * result
				+ (createUserId != null ? createUserId.hashCode() : 0);
		result = 31 * result + (sys ? 1 : 0);
		result = 31
				* result
				+ (authority_resources != null ? authority_resources.hashCode()
						: 0);
		return result;
	}

}
