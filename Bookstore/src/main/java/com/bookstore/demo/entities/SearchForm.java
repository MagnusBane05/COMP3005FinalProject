package com.bookstore.demo.entities;

public class SearchForm {
	private String searchTerm;
	private int minPages;
	private int maxPages;
	private int minPrice;
	private int maxPrice;

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}

	public int getMinPages() {
		return minPages;
	}

	public void setMinPages(int minPages) {
		this.minPages = minPages;
	}

	public int getMaxPages() {
		return maxPages;
	}

	public void setMaxPages(int maxPages) {
		this.maxPages = maxPages;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
}

