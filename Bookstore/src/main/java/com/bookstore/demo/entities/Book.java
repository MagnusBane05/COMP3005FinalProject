package com.bookstore.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

	// TABLE COLUMNS

	@Id
	@Column(name = "ISBN")
	private String ISBN;

	@Column(name = "name", nullable = false, length = 45)
	private String name;

	@Column(name = "author", nullable = false, length = 45)
	private String author;

	@Column(name = "genre", length = 20)
	private String genre;

	@Column(name = "numPages", nullable = false)
	private Integer numPages;

	@Column(name = "quantity")
	private Integer quantity = 0;

	@Column(name = "price", nullable = false, precision = 5, scale = 2)
	private Float price;

	@Column(name = "purchasePrice", nullable = false, precision = 5, scale = 2)
	private Float purchasePrice;

	@ManyToOne
	@JoinColumn(name = "publisher", referencedColumnName = "name")
	private Publisher publisher;

	@Column(name = "salePercentage", precision = 3, scale = 1)
	private Float salePercentage = 0f;

	// CLASS METHODS

	public Book() {

	}

	public boolean matches(String searchTerm, int minPages, int maxPages, int minPrice, int maxPrice) {

		if (price < minPrice)
			return false;

		if (maxPrice > 0 && price > maxPrice)
			return false;

		if (numPages < minPages)
			return false;

		if (maxPages > 0 && numPages > maxPages)
			return false;

		if (searchTerm == null || searchTerm == "")
			return true;
		String filteredSearchTerm = filter(searchTerm);
		if (filter(ISBN).contains(filteredSearchTerm)
				|| filter(name).contains(filteredSearchTerm)
				|| filter(author).contains(filteredSearchTerm)
				|| filter(genre).contains(filteredSearchTerm)
				|| filter(publisher.getName()).contains(filteredSearchTerm))
			return true;
		return false;
	}

	private String filter(String string) {
		return string.replaceAll("[^\\w\\s]", "").toLowerCase();
	}

	public boolean matchesFilter(FilterForm filterForm) {
		if (filterForm.getBookName() != null && filterForm.getBookName() != ""
				&& !filter(name).contains(filter(filterForm.getBookName())))
			return false;

		if (filterForm.getBookAuthor() != null && filterForm.getBookAuthor() != ""
				&& !filter(author).contains(filter(filterForm.getBookAuthor())))
			return false;

		if (filterForm.getBookGenre() != null && filterForm.getBookGenre() != ""
				&& !filter(genre).contains(filter(filterForm.getBookGenre())))
			return false;

		return true;
	}

	// GETTERS AND SETTERS

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getNumPages() {
		return numPages;
	}

	public void setNumPages(Integer numPages) {
		this.numPages = numPages;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Float purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Float getSalePercentage() {
		return salePercentage;
	}

	public void setSalePercentage(Float salePercentage) {
		this.salePercentage = salePercentage;
	}

}
