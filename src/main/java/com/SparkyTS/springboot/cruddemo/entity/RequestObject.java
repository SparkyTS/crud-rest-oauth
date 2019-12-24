package com.SparkyTS.springboot.cruddemo.entity;

import java.sql.Timestamp;

public class RequestObject {
	private int id;
	private String username;
	private String password;
	private boolean enabled;
	private Timestamp createdOn;
	private Timestamp updatedOn;
	private String authority;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public User getUser() {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setEnabled(enabled);
		user.setCreatedOn(createdOn);
		user.setUpdatedOn(updatedOn);
		return user;
	}
	@Override
	public String toString() {
		return "RequestObject [username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", createdOn=" + createdOn + ", updatedOn=" + updatedOn + ", authority=" + authority + "]";
	}
	
}
