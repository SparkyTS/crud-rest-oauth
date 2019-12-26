package com.SparkyTS.springboot.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SparkyTS.springboot.cruddemo.dao.EmployeeDAO;
import com.SparkyTS.springboot.cruddemo.entity.Employee;
import com.SparkyTS.springboot.cruddemo.entity.Response;
import com.SparkyTS.springboot.cruddemo.exeception.EmployeeNotFoundExeception;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class EmployeeRestController {
	
	@Autowired
	private EmployeeDAO employeeDAO;


	@GetMapping("/employees")
	public Response getEmployees(){
		Authentication auth 
        = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("test :::::"+auth.getName());
      if (auth != null) {
      	auth.getAuthorities().forEach(auth1 ->{
      		System.out.println("auth1.getAuthority() ::::"+auth1.getAuthority());
      	});
      }
		List<Employee> employees = employeeDAO.findAll();
		return new Response(HttpStatus.FOUND.value(), "List of all employees", employees);
	}
	
	@GetMapping("/employees/{employeeId}")
	public Response getEmployee(@PathVariable int employeeId) {
		
		Employee employee = employeeDAO.find(employeeId);
		
		if(employee==null)
			throw new EmployeeNotFoundExeception("Please Enter Valid Employee Id");
		
		return new Response(HttpStatus.FOUND.value(), "Requested Employee: ", employee);
	}
	
	@PostMapping("/employees")
	public Response addEmployee(@RequestBody Employee employee) {
		
		employee.setId(0);
		
		return new Response(HttpStatus.FOUND.value(), "Added New Employee: ", employeeDAO.add(employee));
	}
	
	@PutMapping("/employees")
	public Response updateEmployee(@RequestBody Employee employee) {
		return new Response(HttpStatus.FOUND.value(), "Updated Employee : ", employeeDAO.add(employee));
	}
	
	@DeleteMapping("/employees/{employeeId}")
	public Response deleteEmployee(@PathVariable int employeeId) {
		Employee employee = employeeDAO.find(employeeId);
		if(employee==null)
			throw new EmployeeNotFoundExeception("Please Enter Valid Employee Id");
		employeeDAO.delete(employeeId);
		return new Response(HttpStatus.FOUND.value(), "Deleted Employee : ", employee);
	}
}
