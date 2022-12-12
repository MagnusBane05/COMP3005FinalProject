package com.bookstore.demo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.demo.entities.Book;
import com.bookstore.demo.entities.Order;
import com.bookstore.demo.entities.User;
import com.bookstore.demo.repositories.OrderRepository;

@Service
public class OrderService {

	// REPOSITORIES

	@Autowired
	OrderRepository orderRepository;

	// SERVICES

	@Autowired
	UserService userService;

	// CLASS METHODS

	public Order createOrder(String billingInfo, String shippingInfo, User user, List<Book> books) {

		for (Book book : books) {
			int quantity = book.getQuantity();
			if (quantity == 0)
				return null;
			book.setQuantity(quantity - 1);
		}

		Order order = new Order(billingInfo, shippingInfo, LocalDate.now(), user, books);
		orderRepository.save(order);

		return order;
	}

	public List<Order> getOrdersByUser(User user) {
		List<Order> ordersByUser = orderRepository.findAllByUsername(user.getUsername());
		return ordersByUser;
	}

}
