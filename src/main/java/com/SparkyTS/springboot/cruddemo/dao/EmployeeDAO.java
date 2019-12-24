package com.SparkyTS.springboot.cruddemo.dao;

import java.util.List;

import com.SparkyTS.springboot.cruddemo.entity.Employee;

public interface EmployeeDAO {
	
	List<Employee> findAll();
	
	Employee find(int empId);

	Employee add(Employee employee);

	void delete(int employeeId);
}
