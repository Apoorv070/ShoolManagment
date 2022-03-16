package com.Service.School;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolApplication {
	// MicroService 1 Which stores the details of all the schools
	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

}
