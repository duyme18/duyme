package com.hoangducduy.duyme.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.hoangducduy.duyme.models.Author;
import com.hoangducduy.duyme.security.service.AuthorService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/")
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@GetMapping("authors")
	public ResponseEntity<List<Author>> getAllAuthors() {

		List<Author> authors = (List<Author>) authorService.findAll();

		if (authors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(authors, HttpStatus.OK);
	}

	@GetMapping("author/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {

		Optional<Author> author = authorService.findById(id);

		if (author.isPresent()) {
			return new ResponseEntity<>(author.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PostMapping("author")
	public ResponseEntity<Author> addAuthor(@RequestBody Author author) {

		authorService.save(author);
		return new ResponseEntity<>(author, HttpStatus.CREATED);

	}

	@PutMapping("author/{id}")
	public ResponseEntity<Author> updateAuhor(@PathVariable Long id, @RequestBody Author author) {
		Optional<Author> currentAuthor = authorService.findById(id);

		if (currentAuthor.isPresent()) {
			currentAuthor.get().setAuthorName(author.getAuthorName());
			authorService.save(currentAuthor.get());
			return new ResponseEntity<>(currentAuthor.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("author/{id}")
	public ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {

		Optional<Author> author = authorService.findById(id);

		if (author.isPresent()) {
			authorService.remove(id);

			return new ResponseEntity<>(author.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	@GetMapping("author/search")
	public ResponseEntity<?> getAuthorByAuthorName(@RequestParam(value="authorName") String authorName){
		
		List<Author> authors= (List<Author>) authorService.findAll();
		
		List<Author> authors1  = new ArrayList<Author>();
		
		for(Author author : authors) {
			if(author.getAuthorName().contains(authorName)) {
				authors1.add(author);
			}
		}
		return new ResponseEntity<>(authors1, HttpStatus.OK); 
		
	}
}
