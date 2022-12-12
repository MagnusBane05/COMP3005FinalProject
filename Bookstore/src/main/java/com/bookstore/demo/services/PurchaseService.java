package com.bookstore.demo.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.demo.entities.Book;
import com.bookstore.demo.entities.Purchase;
import com.bookstore.demo.repositories.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	PurchaseRepository purchaseRepository;

	public Purchase makePurchase(Book book, int quantity) {
		LocalDate date = LocalDate.now();
		Purchase purchaseInDatabase = purchaseRepository.findByDateAndISBN(date, book.getISBN());
		// new purchase
		if (purchaseInDatabase == null) {
			Purchase purchase = new Purchase(book, date, quantity);
			purchaseRepository.save(purchase);
			return purchase;
		}
		// purchase already exists
		purchaseInDatabase.setQuantity(purchaseInDatabase.getQuantity() + quantity);
		purchaseRepository.save(purchaseInDatabase);
		return purchaseInDatabase;
	}

}
