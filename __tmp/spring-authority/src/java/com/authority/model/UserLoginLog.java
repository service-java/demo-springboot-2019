package com.authority.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.authority.utils.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 用户历史
 * 
 * @author Tony
 * 
 */
@javax.persistence.Table(name = "user_login_log")
@Entity
public class UserLoginLog implements Serializable {

	/** The Constant serialVersionUID. */
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

	private String userId;

	@javax.persistence.Column(name = "user_id")
	@Basic
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private String userName;

	@javax.persistence.Column(name = "user_name")
	@Basic
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	private Date loginTime;

	@JsonSerialize(using = JsonDateSerializer.class)
	@javax.persistence.Column(name = "login_time")
	@Basic
	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	private Date logoutTime;

	@JsonSerialize(using = JsonDateSerializer.class)
	@javax.persistence.Column(name = "logout_time")
	@Basic
	public Date getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(Date logoutTime) {
		this.logoutTime = logoutTime;
	}

	private String loginIp;

	@javax.persistence.Column(name = "login_ip")
	@Basic
	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	private String userAgent;

	@javax.persistence.Column(name = "user_agent")
	@Basic
	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	private Integer loginPort;

	@javax.persistence.Column(name = "login_port")
	@Basic
	public Integer getLoginPort() {
		return loginPort;
	}

	public void setLoginPort(Integer loginPort) {
		this.loginPort = loginPort;
	}

	private Integer loginLocalPort;

	@javax.persistence.Column(name = "login_local_port")
	@Basic
	public Integer getLoginLocalPort() {
		return loginLocalPort;
	}

	public void setLoginLocalPort(Integer loginLocalPort) {
		this.loginLocalPort = loginLocalPort;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserLoginLog that = (UserLoginLog) o;

		if (id != null ? !id.equals(that.id) : that.id != null)
			return false;
		if (loginIp != null ? !loginIp.equals(that.loginIp)
				: that.loginIp != null)
			return false;
		if (loginPort != null ? !loginPort.equals(that.loginPort)
				: that.loginPort != null)
			return false;
		if (loginTime != null ? !loginTime.equals(that.loginTime)
				: that.loginTime != null)
			return false;
		if (logoutTime != null ? !logoutTime.equals(that.logoutTime)
				: that.logoutTime != null)
			return false;
		if (userAgent != null ? !userAgent.equals(that.userAgent)
				: that.userAgent != null)
			return false;
		if (userId != null ? !userId.equals(that.userId) : that.userId != null)
			return false;
		if (userName != null ? !userName.equals(that.userName)
				: that.userName != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (userId != null ? userId.hashCode() : 0);
		result = 31 * result + (userName != null ? userName.hashCode() : 0);
		result = 31 * result + (loginTime != null ? loginTime.hashCode() : 0);
		result = 31 * result + (logoutTime != null ? logoutTime.hashCode() : 0);
		result = 31 * result + (loginIp != null ? loginIp.hashCode() : 0);
		result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
		result = 31 * result + (loginPort != null ? loginPort.hashCode() : 0);
		return result;
	}

}
