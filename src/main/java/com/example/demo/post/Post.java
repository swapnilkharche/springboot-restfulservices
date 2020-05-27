 package com.example.demo.post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.demo.user.User;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;
	
	public Post() {}
	
	
	public Post(Integer id, String description) {
		super();
		this.id = id;
		this.description = description;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	@Override
	public String toString() {
		return "Post [postID=" + id + ", postContent=" + description + "]";
	}


}
