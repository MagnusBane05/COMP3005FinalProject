package com.bookstore.demo.entities;

public class Report {

	private Book book;
	private String ISBN;
	private Integer quantity;

	public Report(Book book, Integer quantity) {
		this.book = book;
		this.quantity = quantity;
	}

	public Report(String ISBN, int quantity) {
		this.setISBN(ISBN);
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

}
