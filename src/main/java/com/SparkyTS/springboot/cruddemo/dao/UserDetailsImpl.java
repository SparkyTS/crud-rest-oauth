package com.SparkyTS.springboot.cruddemo.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.SparkyTS.springboot.cruddemo.entity.UserDetails;

@Repository
public class UserDetailsImpl implements UserDetailsDAO {

	@Autowired
	EntityManager entitiyManager;

	@Override
	public UserDetails getUserDetails() {
		
		// check if user is logged in
		boolean isLoogedIn = false;
		UserDetails userDetails = new UserDetails();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			isLoogedIn = true;
			userDetails.setUsername(auth.getName());
			//if there is more details then we can fetch it from db using username
			//as per db we have only one authority so loop will only run for once
			auth.getAuthorities().forEach(auth1 -> {
				userDetails.setUserRole(auth1.getAuthority());
			});
		}
		
		return isLoogedIn ? userDetails : null;
	}

}
