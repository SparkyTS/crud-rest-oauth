package com.SparkyTS.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.SparkyTS.springboot.cruddemo.entity.Authority;
import com.SparkyTS.springboot.cruddemo.entity.User;
import com.SparkyTS.springboot.cruddemo.entity.UserAuthority;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO{

	@Autowired
	private EntityManager entityManger;
//	@Autowired
//	BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
	public User add(User user, Authority authority) throws Exception {
	
		Session session = entityManger.unwrap(Session.class);
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		session.saveOrUpdate(user);
		
		List<Authority> authorities =  session.createQuery("From Authority where role like '" +authority.getRole() + "'").list();
		
		Integer authId = authorities.get(0).getId();
		authority.setId(authId);
		if(authId==null) 
			throw new Exception("Invalid User Authority Exception");
			
		UserAuthority userAuthority = new UserAuthority();
		userAuthority.setUserId(user.getId());
		userAuthority.setAuthorityId(authId.intValue());
		
		session.saveOrUpdate(userAuthority);
		
		return user;
	}

	@Override
	public void delete(int userId) {
		
		Session session = entityManger.unwrap(Session.class);
		
		User user = session.get(User.class, userId);
		session.createQuery("delete from User where id = " + userId).executeUpdate();	
		session.createQuery("delete from UserAuthority where user_id = " + userId).executeUpdate();
	}

}
