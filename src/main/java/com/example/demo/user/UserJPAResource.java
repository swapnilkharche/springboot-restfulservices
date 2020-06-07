package com.example.demo.user;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserJPAResource {
	@Autowired
	private UserDaoService service;
	@Autowired
	private PostRepository postReporsitory;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	EntityLinks entityLinks;

	// find All Users
	
	@GetMapping(path = "/jpa/users")
	public List<User> findAll() {
		return userRepository.findAll();

	}

	// Find one user
	@GetMapping(path = "/jpa/users/{id}", produces = { "application/hal+json" })
	public ResponseEntity<EntityModel<Optional<User>>> find(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isEmpty()) {
			throw new UserNotFoundException("ID-" + id);
		}

		String location = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();

		// .build().toUriString();
		Link link = new Link(location).withSelfRel();

		EntityModel<Optional<User>> entityModel = new EntityModel<Optional<User>>(user, link);
		return ResponseEntity.ok(entityModel);

	}

	// create user
	// return created user
	@PostMapping(path = "/jpa/users")
	public ResponseEntity<Object> createUser(@RequestBody User user) {
		user.setDate(new Date());
		User savedUser = userRepository.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/users").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	// remove user
	@DeleteMapping(path = "/jpa/users/{id}")
	public void deleteUser(@PathVariable int id) {

		userRepository.deleteById(id);

	}

	// get all posts of user
	@GetMapping(path = "/jpa/users/{id}/posts")
	public List<Post> retrieveAllPosts(@PathVariable int id) {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}

		return user.get().getPosts();

	}
	
	@PostMapping(path = "/jpa/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userOptional = userRepository.findById(id);
		
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-" + id);
		}
		
		User user = userOptional.get();
		
		post.setUser(user);
		
		postReporsitory.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/users").buildAndExpand(post.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}


	
}
