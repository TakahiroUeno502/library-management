package com.example.librarymanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.librarymanagement.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	List<Publisher> findByDeletedAtIsNull();

}
