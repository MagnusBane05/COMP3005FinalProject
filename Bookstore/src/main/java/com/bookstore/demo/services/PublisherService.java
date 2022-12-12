package com.bookstore.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.demo.entities.Publisher;
import com.bookstore.demo.repositories.PublisherRepository;

@Service
public class PublisherService {

	@Autowired
	PublisherRepository publisherRepository;

	public List<Publisher> getPublishers() {
		List<Publisher> publishers = publisherRepository.findAll();
		return publishers;
	}

}
