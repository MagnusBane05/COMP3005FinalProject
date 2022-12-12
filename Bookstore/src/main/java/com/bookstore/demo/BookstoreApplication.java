package com.bookstore.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class BookstoreApplication{

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
}
