package com.bookstore.demo.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@Column(name = "order_number")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderNumber;

	@Column(name = "billing_info", nullable = false)
	private String billingInfo;

	@Column(name = "shipping_info", nullable = false)
	private String shippingInfo;

	@Column(name = "date", nullable = false)
	private LocalDate date;

	@ManyToOne
	@JoinColumn(name = "user", nullable = false)
	private User user;

	@ManyToMany
	@JoinTable(name = "order_books", joinColumns = @JoinColumn(name = "order_number", referencedColumnName = "order_number"), inverseJoinColumns = @JoinColumn(name = "ISBN", referencedColumnName = "ISBN"))
	private List<Book> books;

	public Order() {

	}

	public Order(String billingInfo, String shippingInfo, LocalDate date, User user, List<Book> books) {
		this.billingInfo = billingInfo;
		this.shippingInfo = shippingInfo;
		this.date = date;
		this.user = user;
		this.books = books;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getBillingInfo() {
		return billingInfo;
	}

	public void setBillingInfo(String billingInfo) {
		this.billingInfo = billingInfo;
	}

	public String getShippingInfo() {
		return shippingInfo;
	}

	public void setShippingInfo(String shippingInfo) {
		this.shippingInfo = shippingInfo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}