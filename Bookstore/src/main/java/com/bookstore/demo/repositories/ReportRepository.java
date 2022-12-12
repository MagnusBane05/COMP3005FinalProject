package com.bookstore.demo.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bookstore.demo.entities.Report;

@Repository
public class ReportRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public Float findTotalSales() {
		return jdbcTemplate.queryForObject(
				"SELECT SUM(books.price * (1 - books.sale_percentage)) AS total_sales " +
						"FROM orders " +
						"INNER JOIN order_books ON orders.order_number = order_books.order_number " +
						"INNER JOIN books ON order_books.ISBN = books.ISBN",
						Float.class);
	}

	public Float findSalesByISBN(String ISBN) {
		return jdbcTemplate.queryForObject(
				"SELECT SUM(books.price * (1 - books.sale_percentage)) AS total_sales " +
						"FROM orders " +
						"INNER JOIN order_books ON orders.order_number = order_books.order_number " +
						"INNER JOIN books ON order_books.ISBN = books.ISBN " +
						"WHERE books.ISBN = '" + ISBN + "'",
						Float.class);
	}

	public Float findTotalExpenditures() {
		return jdbcTemplate.queryForObject(
				"SELECT SUM(books.purchase_price * purchases.quantity) AS total_expenditures "
						+ "FROM purchases "
						+ "INNER JOIN books ON purchases.book_ISBN = books.ISBN",
						Float.class);
	}

	public Float findExpendituresByISBN(String ISBN) {
		return jdbcTemplate.queryForObject(
				"SELECT SUM(books.purchase_price * purchases.quantity) AS total_expenditures "
						+ "FROM purchases "
						+ "INNER JOIN books ON purchases.book_ISBN = books.ISBN "
						+ "WHERE books.ISBN = '" + ISBN + "'",
						Float.class);
	}

	public List<Report> findAllPurchases() {
		return jdbcTemplate.query(
				"SELECT books.*, purchases.quantity "
						+ "FROM purchases "
						+ "INNER JOIN books ON purchases.book_ISBN = books.ISBN",
						(rs, rowNum) -> new Report(rs.getString("ISBN"),
								rs.getInt("quantity")));
	}

	public Integer findAmountSoldInLastMonth(String ISBN) {
		String query = "SELECT COUNT(*) "
				+ "FROM books b "
				+ "INNER JOIN order_books ob ON b.ISBN = ob.ISBN "
				+ "INNER JOIN orders o ON ob.order_number = o.order_number "
				+ "WHERE o.date >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH) "
				+ "AND b.ISBN = '" + ISBN + "'";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

}
