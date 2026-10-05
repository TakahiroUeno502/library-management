package com.example.librarymanagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.example.librarymanagement.entity.Publisher;
import com.example.librarymanagement.repository.PublisherRepository;

@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

	private final PublisherRepository publisherRepository;

	public PublisherController(PublisherRepository publisherRepository) {
		this.publisherRepository = publisherRepository;
	}

	@GetMapping
	public List<Publisher> getAllPublishers() {
		return publisherRepository.findByDeletedAtIsNull();
	}

	@PostMapping
	public Publisher createPublisher(@RequestBody Publisher publisher) {
		return publisherRepository.save(publisher);
	}

	@PutMapping("/{id}")
	public Publisher updatePublisher(
		@PathVariable Long id,
		@RequestBody Publisher publisher) {
		Publisher existingPublisher = publisherRepository.findById(id).orElseThrow();
		existingPublisher.setName(publisher.getName());
		return publisherRepository.save(existingPublisher);
	}

	@DeleteMapping("/{id}")
	public Publisher deletePublisher(@PathVariable Long id) {
		Publisher existingPublisher = publisherRepository.findById(id).orElseThrow();
		existingPublisher.setDeletedAt(LocalDateTime.now());
		return publisherRepository.save(existingPublisher);
	}

}

