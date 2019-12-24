package com.SparkyTS.springboot.cruddemo.dao;

import java.util.List;

import com.SparkyTS.springboot.cruddemo.entity.Authority;
import com.SparkyTS.springboot.cruddemo.entity.User;

public interface UserDAO {

	
	List<User> findAll();
	
	User find(int userId);

	User add(User user, Authority authorities) throws Exception;

	void delete(int userId);
}
