package com.notes.model;

public class Note {
	private Integer id;
	private String note;
	private Integer user_id;
	private Integer importance;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNote() {
		return this.note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	public Integer getUser_id() {
		return this.user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public Integer getImportance() {
		return this.importance;
	}
	public void setImportance(Integer importance) {
		this.importance = importance;
	}
}

