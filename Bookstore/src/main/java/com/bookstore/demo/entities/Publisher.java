package com.bookstore.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "publishers")
public class Publisher {

	// TABLE COLUMNS

	@Id
	@Column(name = "name", length = 45)
	private String name;

	@Column(name = "address", length = 45)
	private String address;

	@Column(name = "email", length = 45, unique = true)
	private String email;

	@Column(name = "phone_number", length = 45)
	private String phoneNumber;

	@Column(name = "banking_account", length = 12, unique = true, nullable = false)
	private String bankingAccount;

	// CLASS METHODS

	public Publisher() {
	}

	// GETTERS AND SETTERS

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getBankingAccount() {
		return bankingAccount;
	}

	public void setBankingAccount(String bankingAccount) {
		this.bankingAccount = bankingAccount;
	}

}
