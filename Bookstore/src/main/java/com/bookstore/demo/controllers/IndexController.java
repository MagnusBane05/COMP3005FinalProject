package com.bookstore.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.demo.entities.User;
import com.bookstore.demo.services.UserService;

@Controller
@RequestMapping("")
public class IndexController {

	// SERVICES
	@Autowired
	UserService userService;

	@GetMapping("")
	public String viewHomePage(Model model) {
		List<User> listUsers = userService.getUsers();
		model.addAttribute("listUsers", listUsers);
		return "index2";
	}

	@GetMapping("/register")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register_complete")
	public String registerComplete(User user) {
		// encrypt password
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);

		userService.addUser(user);

		return "redirect:/";
	}

}