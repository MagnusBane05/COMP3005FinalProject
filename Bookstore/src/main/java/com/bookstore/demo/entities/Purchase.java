package com.bookstore.demo.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "purchases")
public class Purchase {

	// TABLE COLUMNS

	@EmbeddedId
	PurchaseID id;

	@MapsId("ISBN")
	@ManyToOne
	Book book;

	@Column(name = "quantity", nullable = false)
	private int quantity;

	// CLASS METHODS

	public Purchase() {
	}

	public Purchase(Book book, LocalDate date, int quantity) {
		this.book = book;
		this.quantity = quantity;
		id = new PurchaseID(date, book.getISBN());
	}

	// GETTERS AND SETTERS

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
