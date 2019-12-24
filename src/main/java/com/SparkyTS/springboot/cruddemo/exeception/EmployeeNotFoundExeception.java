package com.SparkyTS.springboot.cruddemo.exeception;

public class EmployeeNotFoundExeception extends RuntimeException {

	public EmployeeNotFoundExeception(String message) {
		super(message);
	}
}
