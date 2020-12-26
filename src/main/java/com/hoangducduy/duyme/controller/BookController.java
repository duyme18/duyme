package com.hoangducduy.duyme.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoangducduy.duyme.models.Book;
import com.hoangducduy.duyme.repository.BookRepository;

@CrossOrigin(origins = "*")
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

	@GetMapping("book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isPresent()) {
			return new ResponseEntity<>(book.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("book")
	public ResponseEntity<Book> addBook(@RequestBody Book book) {

		Book book1 = book;
		
		bookRepository.save(book1);
		return new ResponseEntity<>(book1, HttpStatus.CREATED);
	}

}
