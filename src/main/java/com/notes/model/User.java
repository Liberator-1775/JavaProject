package com.notes.model;

public class User {
	private Integer id;
	private String name;
	private String passwd;

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
	
	public String getPasswd() {
		return this.passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}

