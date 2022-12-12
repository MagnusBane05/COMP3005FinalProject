package com.bookstore.demo.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PurchaseID implements Serializable {

	// TABLE COLUMNS

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "ISBN")
	private String ISBN;

	// CLASS METHODS

	public PurchaseID() {

	}

	// GETTERS AND SETTERS

	public PurchaseID(LocalDate date, String ISBN) {
		this.date = date;
		this.ISBN = ISBN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseID other = (PurchaseID) obj;
		if (ISBN == null) {
			if (other.ISBN != null)
				return false;
		} else if (!ISBN.equals(other.ISBN))
			return false;
		return true;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
