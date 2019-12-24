package com.SparkyTS.springboot.cruddemo.exeception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class EmployeeNotFoundExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> studentNotFoundHandler(EmployeeNotFoundExeception exe){
		
		EmployeeErrorResponse errorResponse = new EmployeeErrorResponse();
		
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(exe.getMessage());
		errorResponse.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<EmployeeErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> studentNotFoundHandler(Exception exe){
		
		EmployeeErrorResponse errorResponse = new EmployeeErrorResponse();
		
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage("BAD REQUEST : Invalid Employee Id");
		errorResponse.setTimestamp(System.currentTimeMillis());
		
		return new ResponseEntity<EmployeeErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}