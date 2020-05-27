package com.example.demo.employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;

import org.springframework.stereotype.Component;

@Component
public class EmployeeDaoService {
	
	private static List<Employee> employees = new ArrayList<>();
	int empCount =2;
	static {
		employees.add(new Employee(1,"Deepak","Ingole",new Date(), "deep@email.com"));
		employees.add(new Employee(2,"Jack","Mama",new Date(), "jack@email.com"));
	}
	
	//find All
	
	public List<Employee> findAll(){
		return employees;
	}
	
	//find one
	public Employee findOne(int id) {
		for(Employee employee: employees) {
			if(employee.getId() == id) {
				return employee;
			}
		}
		return null;
	}
	
	//save user
	public Employee save(Employee employee) {
		if(employee.getId() == null) {
			employee.setId(++empCount);
		}
		employees.add(employee);
		return employee;
	}
	

	
}
