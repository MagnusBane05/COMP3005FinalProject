package com.bookstore.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "users")
public class User {

	// TABLE COLUMNS

	@Id
	@Column(name = "username")
	protected String username;

	@Column(nullable = false, length = 64)
	protected String password;

	@Column(name = "role")
	protected String role = "CUSTOMER";

	// CLASS METHODS

	public User() {
	}

	// GETTERS AND SETTERS

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}



}
