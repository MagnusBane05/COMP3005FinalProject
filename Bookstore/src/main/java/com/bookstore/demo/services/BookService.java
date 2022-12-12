package com.bookstore.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.demo.EmailService;
import com.bookstore.demo.entities.Book;
import com.bookstore.demo.entities.FilterForm;
import com.bookstore.demo.entities.SearchForm;
import com.bookstore.demo.repositories.BookRepository;

@Service
public class BookService {

	private static final int ORDER_THRESHHOLD = 10;

	// REPOSITORIES

	@Autowired
	BookRepository bookRepository;

	// SERVICES

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	ReportService reportService;

	@Autowired
	EmailService emailService;

	// CLASS METHODS

	public List<Book> getBooks() {
		List<Book> bookList = bookRepository.findAll();
		orderLowStockBooks(bookList);
		return bookList;
	}

	private void orderLowStockBooks(List<Book> bookList) {
		for (Book book : bookList) {
			if (book.getQuantity() < ORDER_THRESHHOLD) {
				orderLowStockBook(book);
			}
		}
	}

	private void orderLowStockBook(Book book) {
		int booksToOrder = reportService.getAmountSoldInLastMonth(book);
		book.setQuantity(book.getQuantity() + booksToOrder);
		saveBook(book);
		String message = "Ordering " + booksToOrder + " copies of " + book.getName();
		emailService.sendEmail(message, book.getPublisher().getEmail());
	}

	public Book getBookByISBN(String ISBN) {
		Book book = bookRepository.findByISBN(ISBN);
		return book;
	}

	public Book addBook(Book book) {
		// save the book
		Book savedBook = bookRepository.save(book);

		// make a purchase
		purchaseService.makePurchase(book, book.getQuantity());

		return savedBook;
	}

	public Book saveBook(Book book) {
		Book bookInDatabase = getBookByISBN(book.getISBN());
		if (bookInDatabase == null)
			return addBook(book);

		changeBookQuantity(book, bookInDatabase);

		// save the book
		Book savedBook = bookRepository.save(book);

		return savedBook;

	}

	private void changeBookQuantity(Book book, Book bookInDatabase) {
		int quantityChange = book.getQuantity() - bookInDatabase.getQuantity();

		// make a purchase
		if (quantityChange > 0)
			purchaseService.makePurchase(book, quantityChange);

	}

	public List<Book> getBooksMatching(SearchForm searchForm) {
		List<Book> books = bookRepository.findAll();
		List<Book> matchingBooks = new ArrayList<>();
		for (Book book : books) {
			if (book.matches(searchForm.getSearchTerm(), searchForm.getMinPages(), searchForm.getMaxPages(),
					searchForm.getMinPrice(), searchForm.getMaxPrice()))
				matchingBooks.add(book);
		}
		return matchingBooks;
	}

	public List<Book> getSoldBooks() {
		return bookRepository.findAllSoldBooks();
	}

	public List<Book> getFilteredBooks(FilterForm filterForm) {
		List<Book> books = getBooks();
		List<Book> filteredBooks = new ArrayList<>();
		for (Book book : books) {
			if (book.matchesFilter(filterForm))
				filteredBooks.add(book);
		}
		return filteredBooks;
	}

	public List<Book> getFilteredSoldBooks(List<Book> books) {
		List<Book> filteredSoldBooks = new ArrayList<>();
		for (Book book : books) {
			filteredSoldBooks.addAll(bookRepository.findFilteredSoldBooks(book.getISBN()));
		}
		return filteredSoldBooks;
	}

}
