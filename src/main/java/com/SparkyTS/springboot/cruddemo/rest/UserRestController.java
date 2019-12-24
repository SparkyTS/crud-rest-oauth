package com.SparkyTS.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public User addUser(@RequestBody User user) {
		user.setId(0);
		for(Authority auth:user.getAuthorities()) {
			auth.setId(0);
		}
		System.out.println("Received for adding new user: " + user);
		return userDAO.add(user);
	}
	
	@PutMapping("/users")
	public User updateUser(@RequestBody User user) {
		return userDAO.add(user);
	}
	
	@DeleteMapping("/users/{id}")
	public User deleteUser(@PathVariable int id) {
		
		User user = userDAO.find(id);
		
//		if(user==null)
//			throw new userNotFoundExeception("Please Enter Valid User_id");
		
		return user;
	}
}
