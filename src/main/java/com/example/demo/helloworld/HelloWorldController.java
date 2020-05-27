package com.example.demo.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	//@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	//Return HelloWorldBean	
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}	
	
	//Step06 Adding Path Variable
	@GetMapping(path="/hello-world/path-var/{name}")
	public HelloWorldBean helloWorldBeanPathVar(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
		
	}
	

}
