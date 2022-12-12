package com.bookstore.demo.repositories;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.demo.entities.Purchase;
import com.bookstore.demo.entities.PurchaseID;

public interface PurchaseRepository extends JpaRepository<Purchase, PurchaseID> {

	@Query(value = "SELECT * FROM purchases WHERE date = ?1 AND book_ISBN = ?2", nativeQuery = true)
	Purchase findByDateAndISBN(LocalDate date, String isbn);

}
