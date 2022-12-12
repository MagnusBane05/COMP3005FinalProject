package com.bookstore.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.demo.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	@Query(value = "SELECT * FROM orders WHERE user = ?1", nativeQuery = true)
	List<Order> findAllByUsername(String username);

}
