package com.authority.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author Tony
 *
 */
@javax.persistence.Table(name = "sys_users")
@Entity
public class SysUsers implements UserDetails {

	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	    private String userName;
	    private String userAccount;
	    private String userPassword;
	    private int userType;
	    private boolean isAccountNonExpired;
	    private Collection<GrantedAuthority> authorities;
	    private String username;
	    private String password;
	    private boolean isAccountNonLocked;
	    private boolean isCredentialsNonExpired;
	    private boolean isEnabled;
	    private boolean isSys;
	    private Collection<SysRoles> user_roles;
	    private String job;
	    private String creatorId;
	    private Date createDate;
	    private Integer maxDataLevel;

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

	    @javax.persistence.Column(name = "user_name", unique = true, nullable = false)
	    @Basic
	    public String getUserName() {
	        return userName;
	    }

	    public void setUserName(String userName) {
	        this.userName = userName;
	    }

	    @javax.persistence.Column(name = "user_account", nullable = false)
	    @Basic
	    public String getUserAccount() {
	        return userAccount;
	    }

	    public void setUserAccount(String userAccount) {
	        this.userAccount = userAccount;
	    }

	    @javax.persistence.Column(name = "user_password")
	    @Basic
	    public String getUserPassword() {
	        return userPassword;
	    }

	    public void setUserPassword(String userPassword) {
	        this.userPassword = userPassword;
	    }

	    @javax.persistence.Column(name = "user_type")
	    @Basic
	    public int getUserType() {
	        return userType;
	    }

	    public void setUserType(int userType) {
	        this.userType = userType;
	    }

	    @Override
	    @Transient
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return this.authorities;  //To change body of implemented methods use File | Settings | File Templates.
	    }

	    public void setAuthorities(Collection<GrantedAuthority> authorities) {
	        this.authorities = authorities;
	    }

	    @Override
	    @Transient
	    public String getPassword() {
	        return this.userPassword;  //To change body of implemented methods use File | Settings | File Templates.
	    }
//	    private Date createDate;
	//
//	    public Date getCreateDate() {
//	        return createDate;
//	    }
	//
//	    public void setCreateDate(Date createDate) {
//	        this.createDate = createDate;
//	    }
	//
//	    private String createUserId;
	//
//	    public String getCreateUserId() {
//	        return createUserId;
//	    }
	//
//	    public void setCreateUserId(String createUserId) {
//	        this.createUserId = createUserId;
//	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    @Override
	    @Transient
	    public String getUsername() {
	        return this.userAccount;  //To change body of implemented methods use File | Settings | File Templates.
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    @javax.persistence.Column(name = "is_account_non_expired")
	    @Basic
	    public boolean isAccountNonExpired() {
	        return isAccountNonExpired;
	    }

	    public void setAccountNonExpired(boolean accountNonExpired) {
	        isAccountNonExpired = accountNonExpired;
	    }

	    @javax.persistence.Column(name = "is_account_non_locked")
	    @Basic
	    public boolean isAccountNonLocked() {
	        return isAccountNonLocked;
	    }

	    public void setAccountNonLocked(boolean accountNonLocked) {
	        isAccountNonLocked = accountNonLocked;
	    }

	    @javax.persistence.Column(name = "is_credentials_non_expired")
	    @Basic
	    public boolean isCredentialsNonExpired() {
	        return isCredentialsNonExpired;
	    }

	    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
	        isCredentialsNonExpired = credentialsNonExpired;
	    }

	    @javax.persistence.Column(name = "is_enabled")
	    @Basic
	    public boolean isEnabled() {
	        return isEnabled;
	    }

	    public void setEnabled(boolean enabled) {
	        isEnabled = enabled;
	    }

	    @javax.persistence.Column(name = "is_sys")
	    @Basic
	    public boolean isSys() {
	        return isSys;
	    }

	    public void setSys(boolean sys) {
	        isSys = sys;
	    }

	    @javax.persistence.JoinTable(name = "sys_users_roles", joinColumns = @javax.persistence.JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @javax.persistence.JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false))
	    @ManyToMany
	    public Collection<SysRoles> getUser_roles() {
	        return user_roles;
	    }

	    public void setUser_roles(Collection<SysRoles> user_roles) {
	        this.user_roles = user_roles;
	    }

	    @Transient
	    public Integer getMaxDataLevel() {
	        return maxDataLevel;
	    }

	    public void setMaxDataLevel(Integer maxDataLevel) {
	        this.maxDataLevel = maxDataLevel;
	    }

	    @Column(name = "job")
	    @Basic
	    public String getJob() {
	        return job;
	    }

	    public void setJob(String job) {
	        this.job = job;
	    }

	    @Column(name = "creator_id")
	    @Basic
	    public String getCreatorId() {
	        return creatorId;
	    }

	    public void setCreatorId(String creatorId) {
	        this.creatorId = creatorId;
	    }

	    @Column(name = "create_date")
	    @Basic
	    public Date getCreateDate() {
	        return createDate;
	    }

	    public void setCreateDate(Date createDate) {
	        this.createDate = createDate;
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;

	        SysUsers mSysUsers = (SysUsers) o;

	        if (isAccountNonExpired != mSysUsers.isAccountNonExpired) return false;
	        if (isAccountNonLocked != mSysUsers.isAccountNonLocked) return false;
	        if (isCredentialsNonExpired != mSysUsers.isCredentialsNonExpired) return false;
	        if (isEnabled != mSysUsers.isEnabled) return false;
	        if (isSys != mSysUsers.isSys) return false;
	        if (userType != mSysUsers.userType) return false;
	        if (createDate != null ? !createDate.equals(mSysUsers.createDate) : mSysUsers.createDate != null) return false;
	        if (creatorId != null ? !creatorId.equals(mSysUsers.creatorId) : mSysUsers.creatorId != null) return false;
	        if (id != null ? !id.equals(mSysUsers.id) : mSysUsers.id != null) return false;
	        if (password != null ? !password.equals(mSysUsers.password) : mSysUsers.password != null) return false;
	        if (userAccount != null ? !userAccount.equals(mSysUsers.userAccount) : mSysUsers.userAccount != null)
	            return false;
	        if (userName != null ? !userName.equals(mSysUsers.userName) : mSysUsers.userName != null) return false;
	        if (userPassword != null ? !userPassword.equals(mSysUsers.userPassword) : mSysUsers.userPassword != null)
	            return false;
	        if (username != null ? !username.equals(mSysUsers.username) : mSysUsers.username != null) return false;

	        return true;
	    }

	    @Override
	    public int hashCode() {
	        int result = id != null ? id.hashCode() : 0;
	        result = 31 * result + (userName != null ? userName.hashCode() : 0);
	        result = 31 * result + (userAccount != null ? userAccount.hashCode() : 0);
	        result = 31 * result + (userPassword != null ? userPassword.hashCode() : 0);
	        result = 31 * result + userType;
	        result = 31 * result + (isAccountNonExpired ? 1 : 0);
	        result = 31 * result + (username != null ? username.hashCode() : 0);
	        result = 31 * result + (password != null ? password.hashCode() : 0);
	        result = 31 * result + (isAccountNonLocked ? 1 : 0);
	        result = 31 * result + (isCredentialsNonExpired ? 1 : 0);
	        result = 31 * result + (isEnabled ? 1 : 0);
	        result = 31 * result + (isSys ? 1 : 0);
	        result = 31 * result + (creatorId != null ? creatorId.hashCode() : 0);
	        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
	        return result;
	    }
	    
}
