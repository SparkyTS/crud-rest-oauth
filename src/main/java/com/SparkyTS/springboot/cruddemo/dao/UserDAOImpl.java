package com.SparkyTS.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SparkyTS.springboot.cruddemo.entity.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

	@Autowired
	private EntityManager entityManger;
	
	@Override
	public List<User> findAll() {
		
		Session session = entityManger.unwrap(Session.class);
		
		return session.createQuery("from User").list();
	}

	@Override
	public User find(int userId) {
		Session session = entityManger.unwrap(Session.class);
		
		return session.get(User.class,userId);
	}

	@Override
	public User add(User user) {
	
		Session session = entityManger.unwrap(Session.class);
		
		session.saveOrUpdate(user);
		
		return user;
	}

	@Override
	public void delete(int userId) {
		
		Session session = entityManger.unwrap(Session.class);
		
		session.createQuery("delete from User where id = " + userId).executeUpdate();
	}

}
