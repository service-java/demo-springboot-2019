package com.itzcn.bean;

public class MyBean {
	
	private String userName = null;//�û���
	private String userPass = null;//����
	private int count = 0;//����

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	

	public int getCount() {
		count ++;
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
