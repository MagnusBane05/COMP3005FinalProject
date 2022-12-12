package com.bookstore.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.demo.entities.Book;
import com.bookstore.demo.entities.Report;
import com.bookstore.demo.repositories.ReportRepository;

@Service
public class ReportService {

	@Autowired
	ReportRepository reportRepository;

	@Autowired
	BookService bookService;

	public Float getTotalSales() {
		Float totalSales = reportRepository.findTotalSales();
		return totalSales == null ? 0f : totalSales;
	}

	public Float getTotalExpenditures() {
		Float totalExpenditures = reportRepository.findTotalExpenditures();
		return totalExpenditures == null ? 0f : totalExpenditures;
	}

	public List<Report> getPurchases() {
		List<Report> reports = reportRepository.findAllPurchases();
		for (Report report : reports) {
			Book book = bookService.getBookByISBN(report.getISBN());
			report.setBook(book);
		}
		return reports;
	}

	public Float getSalesByBooks(List<Book> books) {
		Float totalSales = 0f;
		for (Book book : books) {
			Float salesByISBN = reportRepository.findSalesByISBN(book.getISBN());
			totalSales += salesByISBN == null ? 0f : salesByISBN;
		}
		return totalSales;
	}

	public Float getExpendituresByBooks(List<Book> books) {
		Float totalExpenditures = 0f;
		for (Book book : books) {
			Float expendituresByISBN = reportRepository.findExpendituresByISBN(book.getISBN());
			totalExpenditures += expendituresByISBN == null ? 0f : expendituresByISBN;
		}
		return totalExpenditures;
	}

	public List<Report> getFilteredPurchases(List<Book> filteredBooks) {
		List<Report> reports = getPurchases();
		List<Report> filteredReports = new ArrayList<>();
		for (Report report : reports) {
			if (filteredBooks.contains(report.getBook()))
				filteredReports.add(report);
		}
		return filteredReports;
	}

	public int getAmountSoldInLastMonth(Book book) {
		return reportRepository.findAmountSoldInLastMonth(book.getISBN());
	}

}
