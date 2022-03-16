package com.Service.User;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication {
    // Microservice 2 which stores the details of users
	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

}
