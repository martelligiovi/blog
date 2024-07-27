package com.system.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BlogSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogSystemApplication.class, args);
	}

}
