package com.itzcn.bean;

import java.io.Serializable;

public class Student implements Serializable {
	
	public Student() {//�޲����Ĺ��캯��
		super();
	}
	private String name;//ѧ������
	private String sex;//ѧ���Ա�
	private int age;//ѧ������
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}	

}
