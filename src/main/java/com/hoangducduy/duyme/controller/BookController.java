package com.hoangducduy.duyme.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangducduy.duyme.exception.ResourceNotFoundException;
import com.hoangducduy.duyme.models.Author;
import com.hoangducduy.duyme.models.Book;
import com.hoangducduy.duyme.payload.response.MessageResponse;
import com.hoangducduy.duyme.repository.AuthorRepository;
import com.hoangducduy.duyme.repository.BookRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/")
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	ServletContext context;

	@GetMapping("books")
	public List<Book> getAllBooks() {

		System.out.println("Get all books...");
		List<Book> books = new ArrayList<>();
		bookRepository.findAll().forEach(books::add);

		return books;

	}

	@GetMapping("book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id) throws ResourceNotFoundException {

		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));

		return ResponseEntity.ok().body(book);
	}

	@PostMapping("book")
	public Book addBook(@RequestBody Book book) {

		return bookRepository.save(book);

	}

//	@PostMapping("/book")
//	public ResponseEntity<MessageResponse> createBook(@RequestParam("file") MultipartFile file,
//			@RequestParam("book") String book) throws JsonParseException, JsonMappingException, Exception {
//		System.out.println("Ok .............");
//		Book bookNew = new ObjectMapper().readValue(book, Book.class);
//		boolean isExit = new File(context.getRealPath("/Images/")).exists();
//		if (!isExit) {
//			new File(context.getRealPath("/Images/")).mkdir();
//			System.out.println("mk dir.............");
//		}
//		String fileName = file.getOriginalFilename();
//		String newFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(fileName);
//		File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));
//		try {
//			System.out.println("Image");
//			FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		bookNew.setFileName(newFileName);
//		Book book1 = bookRepository.save(bookNew);
//		if (book1 != null) {
//			return new ResponseEntity<MessageResponse>(new MessageResponse(""), HttpStatus.OK);
//		} else {
//			return new ResponseEntity<MessageResponse>(new MessageResponse("Book not saved"), HttpStatus.BAD_REQUEST);
//		}
//	}

	@PutMapping("book/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable Long id, @Valid @RequestBody Book bookDetails)
			throws ResourceNotFoundException {

		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + id));

		book.setBookName(bookDetails.getBookName());
		book.setTranslator(bookDetails.getTranslator());
		book.setBookAmount(bookDetails.getBookAmount());
		book.setPublishingYear(bookDetails.getPublishingYear());
		book.setCategoryBook(bookDetails.getCategoryBook());
		book.setBookDescription(bookDetails.getBookDescription());
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

	@GetMapping("author/books/{id}")
	public ResponseEntity<?> findByAuthor(@PathVariable Long id) throws ResourceNotFoundException {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + id));

		List<Book> books = bookRepository.findByAuthor(author);

		return ResponseEntity.ok().body(books);
	}
}
