package com.bookstore.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.bookstore.demo.entities.User;
import com.bookstore.demo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}

	public User getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}

	public User getLoggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return getUserByUsername(auth.getName());
	}

	public void addUser(User user) {
		userRepository.save(user);
	}

}
