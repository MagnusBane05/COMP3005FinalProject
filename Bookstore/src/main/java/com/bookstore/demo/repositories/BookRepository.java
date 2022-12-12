package com.bookstore.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.demo.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	@Query(value = "SELECT * FROM books WHERE isbn = ?1", nativeQuery = true)
	Book findByISBN(String ISBN);

	@Query(value = "SELECT books.* "
			+ "FROM orders "
			+ "INNER JOIN order_books ON orders.order_number = order_books.order_number "
			+ "INNER JOIN books ON order_books.ISBN = books.ISBN", nativeQuery = true)
	List<Book> findAllSoldBooks();

	@Query(value = "SELECT books.* FROM orders INNER JOIN order_books ON orders.order_number = order_books.order_number INNER JOIN books ON order_books.ISBN = books.ISBN WHERE books.ISBN = ?1", nativeQuery = true)
	List<Book> findFilteredSoldBooks(String ISBN);

}
