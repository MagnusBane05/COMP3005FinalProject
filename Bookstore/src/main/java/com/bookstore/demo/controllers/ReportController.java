package com.bookstore.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.demo.entities.Book;
import com.bookstore.demo.entities.FilterForm;
import com.bookstore.demo.entities.Report;
import com.bookstore.demo.services.BookService;
import com.bookstore.demo.services.ReportService;

@Controller
@RequestMapping("/reports")
public class ReportController {

	// SERVICES

	@Autowired
	ReportService reportService;

	@Autowired
	BookService bookService;

	@ModelAttribute("filterForm")
	public FilterForm filterForm() {
		FilterForm filterForm = new FilterForm();
		return filterForm;
	}

	// REQUEST MAPPINGS

	@GetMapping()
	public String viewReports(Model model) {
		Float sales = reportService.getTotalSales();
		Float expenditures = reportService.getTotalExpenditures();
		Float profit = sales - expenditures;
		model.addAttribute("sales", sales);
		model.addAttribute("expenditures", expenditures);
		model.addAttribute("profit", profit);

		List<Book> soldBooks = bookService.getSoldBooks();
		model.addAttribute("soldBooks", soldBooks);

		List<Report> purchaseReports = reportService.getPurchases();
		model.addAttribute("purchaseReports", purchaseReports);

		return "reports";
	}

	@PostMapping("/filter")
	public String filterReports(Model model, FilterForm filterForm) {
		System.out.println("hello");
		List<Book> filteredBooks = bookService.getFilteredBooks(filterForm);

		Float sales = reportService.getSalesByBooks(filteredBooks);
		Float expenditures = reportService.getExpendituresByBooks(filteredBooks);
		Float profit = sales - expenditures;
		model.addAttribute("sales", sales);
		model.addAttribute("expenditures", expenditures);
		model.addAttribute("profit", profit);

		List<Book> soldBooks = bookService.getFilteredSoldBooks(filteredBooks);
		model.addAttribute("soldBooks", soldBooks);

		List<Report> purchaseReports = reportService.getFilteredPurchases(filteredBooks);
		model.addAttribute("purchaseReports", purchaseReports);

		return "reports";
	}

}
