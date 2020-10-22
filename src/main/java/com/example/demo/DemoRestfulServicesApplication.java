package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoRestfulServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoRestfulServicesApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/jpa/users").allowedOrigins("http://localhost:4200");
				registry.addMapping("/**").allowedOrigins("http://localhost:4200");
			}
		};
	}

}
