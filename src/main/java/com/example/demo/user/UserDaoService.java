package com.example.demo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	static int userCount =3;
	static {
		users.add(new User(1, "Jack", new Date()));
		users.add(new User(2, "Adam", new Date()));
		users.add(new User(3, "Hari", new Date()));
	}
	
	//find all
	public List<User> findAll(){
		return users;
		
	}
	
	//save user
	public User save(User user) {
		if(user.getId()==null) {
			user.setId(++userCount);
		}
		users.add(user);
		
		return user;
	}
	
	public User findOne(int id) {
		for(User user: users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
		
	}
	
	//delete user
	public User deleteOne(int id) {
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			User user = iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		
		return null;
		
	}

}
