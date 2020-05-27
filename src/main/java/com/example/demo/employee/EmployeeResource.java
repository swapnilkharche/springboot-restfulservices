package com.example.demo.employee;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EmployeeResource {
	@Autowired
	private EmployeeDaoService service;
	
	// find all
	@GetMapping(path="/employees")
	public List<Employee> retrieveAllEmployees(){
		return service.findAll();
	}
	
	//find one
	@GetMapping(path="/employees/{id}")
	public Employee retieveEmployee(@PathVariable int id) {
		return service.findOne(id);
	}
	
	//save employee
	@PostMapping(path="/employees")
	public ResponseEntity<Object> createEmployee(@RequestBody Employee emp) {
		Employee savedEmp = service.save(emp);
		
//		find location i.e. URI of the newly added employee which you 
//		 will find at /user/{id}
		URI location = ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedEmp.getId()).toUri();
		
		//to return created status
		return ResponseEntity.created(location).build();
	}
}
