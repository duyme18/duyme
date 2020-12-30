package com.hoangducduy.duyme.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hoangducduy.duyme.exception.ResourceNotFoundException;
import com.hoangducduy.duyme.models.Book;
import com.hoangducduy.duyme.repository.BookRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("books")
	public List<Book> getAllBooks() {

		return bookRepository.findAll();

	}

	@GetMapping("book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) throws ResourceNotFoundException {

		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));

		return ResponseEntity.ok().body(book);
	}

	@PostMapping("book")
	public Book addBook(@Valid @RequestBody Book book) {

		return bookRepository.save(book);

	}

	@PutMapping("book/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id,@Valid @RequestBody Book bookDetails)
			throws ResourceNotFoundException {

		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));

		book.setBookName(bookDetails.getBookName());
		book.setTranslator(bookDetails.getTranslator());
		book.setBookAmount(bookDetails.getBookAmount());
		book.setPublishingYear(bookDetails.getPublishingYear());
		book.setCategoryBook(bookDetails.getCategoryBook());
		book.setAuthor(bookDetails.getAuthor());
		book.setPublishingCompany(bookDetails.getPublishingCompany());

		final Book updateBook = bookRepository.save(book);

		return ResponseEntity.ok(updateBook);

	}

	@DeleteMapping("book/{id}")
	public Map<String, Boolean> deleteBook(@PathVariable Long id) throws ResourceNotFoundException {

		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));

		bookRepository.delete(book);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;

	}

	@GetMapping("book/search")
	public ResponseEntity<?> getBookByBookName(@RequestParam(value = "bookName") String bookName) {

		List<Book> books = (List<Book>) bookRepository.findAll();

		List<Book> book1 = new ArrayList<Book>();

		for (Book book : books) {
			if (book.getBookName().contains(bookName)) {
				book1.add(book);
			}
		}
		return new ResponseEntity<>(book1, HttpStatus.OK);

	}
}
