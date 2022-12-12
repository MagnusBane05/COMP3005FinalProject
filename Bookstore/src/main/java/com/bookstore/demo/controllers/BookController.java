package com.bookstore.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.demo.entities.Book;
import com.bookstore.demo.entities.Publisher;
import com.bookstore.demo.entities.SearchForm;
import com.bookstore.demo.services.BookService;
import com.bookstore.demo.services.PublisherService;

@Controller
@RequestMapping("/books")
public class BookController {

	// SERVICES

	@Autowired
	BookService bookService;

	@Autowired
	PublisherService publisherService;

	// GET MAPPINGS

	@ModelAttribute("searchForm")
	public SearchForm searchForm() {
		SearchForm searchForm = new SearchForm();
		searchForm.setSearchTerm("");
		return searchForm;
	}

	@GetMapping("")
	public String getAll(Model model) {
		List<Book> bookList = bookService.getBooks();
		model.addAttribute("bookList", bookList);

		return "books";
	}

	@GetMapping("/{ISBN}")
	public String getBook(@PathVariable("ISBN") String ISBN, Model model) {
		Book book = bookService.getBookByISBN(ISBN);
		model.addAttribute("book", book);
		return "book-details";
	}

	@GetMapping("/purchase")
	public String getBookPurchasePage(Model model, Book book) {
		List<Publisher> publishers = publisherService.getPublishers();
		if (book == null)
			book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("publishers", publishers);
		return "purchase_book";

	}

	// POST MAPPINGS

	@PostMapping("")
	public String purchaseBook(Book book) {
		Book bookInDatabase = bookService.getBookByISBN(book.getISBN());
		if (bookInDatabase == null)
			bookService.addBook(book);
		else {
			bookService.saveBook(book);
		}
		return "redirect:/books";
	}

	@PostMapping("/search")
	public String searchBooks(Model model, SearchForm searchForm) {
		List<Book> matchingBooks = bookService.getBooksMatching(searchForm);
		model.addAttribute("bookList", matchingBooks);
		return "books";
	}

}
