package com.example.demo.employee;

import java.util.Date;

import javax.validation.constraints.Email;


public class Employee {
	
	private Integer id;
	private String firstName;
	private String lastName;
	private Date date;
//	private Email email;
	private String email;
	
	
	public Employee(Integer id, String firstName, String lastName, Date date, String string) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.date = date;
		this.email = string;
	}

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", date=" + date
				+ ", email=" + email + "]";
	}
	
	
	
	
	
}
