package com.itzcn.dao;

public class User {
	
	private Integer id;
	private String name;
	private String pass;



	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	} 
	
	public User(Integer id,String name, String pass){
		this.id = id;
		this.name = name;
		this.pass = pass;
	}

}
