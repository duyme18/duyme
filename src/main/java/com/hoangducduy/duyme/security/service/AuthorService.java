package com.hoangducduy.duyme.security.service;

import java.util.Optional;

import com.hoangducduy.duyme.models.Author;

public interface AuthorService {

	Iterable<Author> findAll();
	
	Optional<Author> findById(Long id);
	
	void remove(Long id);
	
	void save(Author author);
	
}
