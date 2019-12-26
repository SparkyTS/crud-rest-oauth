package com.SparkyTS.springboot.cruddemo.entity;

public class UserDetails {
	
	private String username;
	private String userRole;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	@Override
	public String toString() {
		return "UserDetails [username=" + username + ", userRole=" + userRole + "]";
	}
	
}
