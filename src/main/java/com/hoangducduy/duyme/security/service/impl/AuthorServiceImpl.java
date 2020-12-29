package com.hoangducduy.duyme.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoangducduy.duyme.models.Author;
import com.hoangducduy.duyme.repository.AuthorRepository;
import com.hoangducduy.duyme.security.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Iterable<Author> findAll() {
		return authorRepository.findAll();
	}

	@Override
	public Optional<Author> findById(Long id) {
		return authorRepository.findById(id);
	}

	@Override
	public void remove(Long id) {
		authorRepository.deleteById(id);
	}

	@Override
	public void save(Author author) {
		authorRepository.save(author);
	}

	@Override
	public Iterable<Author> findByAuthorName(String authorName) {
		return authorRepository.findByAuthorName(authorName);
	}

}
