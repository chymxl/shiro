package com.chy.shiro.entity;

public class PasswordSalt {
	
	private String id;
	private int salt;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSalt() {
		return salt;
	}
	public void setSalt(int salt) {
		this.salt = salt;
	}

}
