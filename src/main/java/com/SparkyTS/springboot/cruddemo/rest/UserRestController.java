package com.SparkyTS.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SparkyTS.springboot.cruddemo.dao.UserDAO;
import com.SparkyTS.springboot.cruddemo.entity.Authority;
import com.SparkyTS.springboot.cruddemo.entity.RequestObject;
import com.SparkyTS.springboot.cruddemo.entity.User;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	@Autowired
	private UserDAO userDAO;
	
	
	@GetMapping("/users")
	public List<User> getUsers(){
		List<User> users = userDAO.findAll();
		return users;
	}
	
	@GetMapping("/users/{id}")
	public User getUser(@PathVariable int id){
		return userDAO.find(id);
	}
	
	@PostMapping("/users")
	public User addUser(@RequestBody RequestObject obj) throws Exception {
		System.out.println("Received for adding new user: " + obj);
		User user = obj.getUser();
		Authority authority = new Authority(obj.getAuthority());
		userDAO.add(user, authority);
		return user;
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody RequestObject obj) throws Exception {
		System.out.println(obj.getUser() + " " +new Authority(obj.getAuthority()));
		return userDAO.add(obj.getUser(),new Authority(obj.getAuthority()));
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) throws Exception {
		
		User user = userDAO.find(id);
		
		if(user==null)
			throw new Exception("Please Enter Valid User_id");
		
		userDAO.delete(id);
		
		return user;
	}
}
