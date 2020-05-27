package com.example.demo.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;



@RestController
public class UserResource {
	@Autowired
	private UserDaoService service;
	@Autowired 
	EntityLinks entityLinks;
	//find All Users
	@GetMapping(path="/users")
	public List<User> findAll(){
		return service.findAll();		
	}
	
	//Find one user
	@GetMapping(path="/users/{id}", produces={ "application/hal+json" })
	public ResponseEntity<EntityModel<User>> find(@PathVariable int id) {
		User user = service.findOne(id);
		if(user == null) {
			throw new UserNotFoundException("ID-"+id);
		}
		String location = ServletUriComponentsBuilder
				.fromCurrentRequestUri().toUriString();
			
				//.build().toUriString();
		Link link = new Link(location).withSelfRel();
		
		
		EntityModel<User> entityModel = new EntityModel<User>(user, link);
		
		return ResponseEntity.ok(entityModel);
	 

	}	

	//create user
	//return created user
	@PostMapping(path="/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		User savedUser = service.save(user);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/users")
				.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	//remove user
	@DeleteMapping(path="/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteOne(id);
		
		if(user ==null)
			throw new UserNotFoundException("id-" +id);
		
	}
}
