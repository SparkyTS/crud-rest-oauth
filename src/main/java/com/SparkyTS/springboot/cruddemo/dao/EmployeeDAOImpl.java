package com.SparkyTS.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SparkyTS.springboot.cruddemo.entity.Employee;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO{

	@Autowired
	private EntityManager entityManger;

	@Override
	public List<Employee> findAll() {
		
		Session session = entityManger.unwrap(Session.class);
		
		return session.createQuery("from Employee").list();
	}

	@Override
	public Employee find(int empId) {
		
		Session session = entityManger.unwrap(Session.class);
		
		return session.get(Employee.class,empId);
	}

	@Override
	public Employee add(Employee employee) {
		
		Session session = entityManger.unwrap(Session.class);

		session.saveOrUpdate(employee);
		
		return employee;
	}

	@Override
	public void delete(int employeeId) {
		
		Session session = entityManger.unwrap(Session.class);
		
		session.createQuery("delete from Employee where id = " + employeeId).executeUpdate();
	}
}
