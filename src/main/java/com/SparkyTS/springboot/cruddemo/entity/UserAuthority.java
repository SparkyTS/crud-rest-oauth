package com.SparkyTS.springboot.cruddemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_authorities")
public class UserAuthority {

	@Id
	@Column(name="user_id")
	private int userId;
	
	@Column(name="authority_id")
	private int authorityId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}

	@Override
	public String toString() {
		return "UserAuthority [userId=" + userId + ", authorityId=" + authorityId + "]";
	}
	
}
