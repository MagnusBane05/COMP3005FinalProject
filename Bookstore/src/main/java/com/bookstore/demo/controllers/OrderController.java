package com.bookstore.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.demo.entities.Book;
import com.bookstore.demo.entities.Order;
import com.bookstore.demo.entities.User;
import com.bookstore.demo.services.BookService;
import com.bookstore.demo.services.OrderService;
import com.bookstore.demo.services.UserService;

@Controller
@RequestMapping("/orders")
public class OrderController {

	// SERVICES

	@Autowired
	OrderService orderService;

	@Autowired
	BookService bookService;

	@Autowired
	UserService userService;

	// GET MAPPINGS

	@GetMapping("")
	public String viewOrderPage(Model model) {

		User loggedInUser = userService.getLoggedInUser();
		List<Order> orders = orderService.getOrdersByUser(loggedInUser);

		model.addAttribute("orders", orders);

		return "orders";
	}

	// POST MAPPINGS

	@PostMapping("")
	public String orderBooks(@RequestParam("selectedBooks") String[] selectedBooks) {

		String billingInfo = "";
		String shippingInfo = "";
		User loggedInUser = userService.getLoggedInUser();

		List<Book> books = new ArrayList<>();
		for (String ISBN : selectedBooks) {
			Book book = bookService.getBookByISBN(ISBN);
			books.add(book);
		}

		orderService.createOrder(billingInfo, shippingInfo, loggedInUser, books);
		return "redirect:/books";
	}

}
