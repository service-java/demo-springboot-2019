package com.itzcn.bean;

import java.io.Serializable;

public class Email implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String mail = null;//Email��ַ
	private boolean isMail = false;//�Ƿ�Ϊһ����׼��Email��ַ
	public Email() {//Ĭ���޲����Ĺ��캯��
		super();
		// TODO Auto-generated constructor stub
	}
	public Email(String mail) {//����Ϊmail�Ĺ��췽��
		this.mail = mail;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public boolean isMail() {
		String regex =  "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		if (mail.matches(regex)) {
			isMail = true;
		}
		return isMail;
	}
	public void setMail(boolean isMail) {
		this.isMail = isMail;
	}
}
