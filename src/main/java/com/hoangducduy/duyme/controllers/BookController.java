package com.hoangducduy.duyme.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoangducduy.duyme.models.Book;
import com.hoangducduy.duyme.repository.BookRepository;

@RestController
@RequestMapping("/api/auth/")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("books")
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		if (books.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(books, HttpStatus.OK);
	}

	@PostMapping("book")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {
		bookRepository.save(book);
		return new ResponseEntity<>(book, HttpStatus.CREATED);
	}
}
