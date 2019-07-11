/*
 * 
 */
package com.authority.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author Tony
 * 
 */
@javax.persistence.Table(name = "sys_roles")
@Entity
public class SysRoles implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String roleName;
	private String roleDesc;
	private boolean isEnabled;
	private Date createDate;
	private String createUserId;
	private boolean isSys;
	private Integer dataLevel;
	private Collection<SysUsers> role_users;
	private Collection<SysAuthorities> role_authorities = new ArrayList<SysAuthorities>();

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

	@javax.persistence.Column(name = "role_name")
	@Basic
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@javax.persistence.Column(name = "role_desc")
	@Basic
	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@javax.persistence.Column(name = "is_enabled")
	@Basic
	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean enabled) {
		isEnabled = enabled;
	}

	@javax.persistence.Column(name = "create_date")
	@Basic
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@javax.persistence.Column(name = "create_user_id")
	@Basic
	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	@javax.persistence.Column(name = "is_sys")
	@Basic
	public boolean isSys() {
		return isSys;
	}

	public void setSys(boolean sys) {
		isSys = sys;
	}

	@Column(name = "data_level")
	@Basic
	public Integer getDataLevel() {
		return dataLevel;
	}

	public void setDataLevel(Integer dateLevel) {
		this.dataLevel = dateLevel;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		SysRoles mSysRoles = (SysRoles) o;

		if (isEnabled != mSysRoles.isEnabled)
			return false;
		if (isSys != mSysRoles.isSys)
			return false;
		if (createDate != null ? !createDate.equals(mSysRoles.createDate)
				: mSysRoles.createDate != null)
			return false;
		if (createUserId != null ? !createUserId.equals(mSysRoles.createUserId)
				: mSysRoles.createUserId != null)
			return false;
		if (id != null ? !id.equals(mSysRoles.id) : mSysRoles.id != null)
			return false;
		if (roleDesc != null ? !roleDesc.equals(mSysRoles.roleDesc)
				: mSysRoles.roleDesc != null)
			return false;
		if (roleName != null ? !roleName.equals(mSysRoles.roleName)
				: mSysRoles.roleName != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
		result = 31 * result + (roleDesc != null ? roleDesc.hashCode() : 0);
		result = 31 * result + (isEnabled ? 1 : 0);
		result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
		result = 31 * result
				+ (createUserId != null ? createUserId.hashCode() : 0);
		result = 31 * result + (isSys ? 1 : 0);
		return result;
	}

	@ManyToMany(mappedBy = "user_roles", fetch = FetchType.LAZY)
	public Collection<SysUsers> getRole_users() {
		return role_users;
	}

	public void setRole_users(Collection<SysUsers> role_users) {
		this.role_users = role_users;
	}

	@javax.persistence.JoinTable(name = "sys_roles_anthorities", joinColumns = @javax.persistence.JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "authority_id", referencedColumnName = "id", nullable = false))
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE,
			CascadeType.PERSIST })
	public Collection<SysAuthorities> getRole_authorities() {
		return role_authorities;
	}

	public void setRole_authorities(Collection<SysAuthorities> role_authorities) {
		this.role_authorities = role_authorities;
	}

}
