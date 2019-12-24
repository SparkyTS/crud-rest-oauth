package com.SparkyTS.springboot.cruddemo.entity;

import java.util.List;

import org.springframework.http.ResponseEntity;

public class Response{

	private int status;
	
	private String message;
	
	private Object payload;
	
	public Response() { }
	
	public Response(int status, String message, List<Employee> employeesOrUser) {
		this.status = status;
		this.message = message;
		this.payload = employeesOrUser;
	}

	public Response(int status, String message, Employee employees) {
		this.status = status;
		this.message = message;
		this.payload = employees;
	}
	
	public Response(int status, String message, User user) {
		this.status = status;
		this.message = message;
		this.payload = user;		
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}
	
}
