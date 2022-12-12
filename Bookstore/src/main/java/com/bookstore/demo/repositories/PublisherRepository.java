package com.bookstore.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.demo.entities.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, String> {

}
