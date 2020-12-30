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
import com.hoangducduy.duyme.models.Author;
import com.hoangducduy.duyme.repository.AuthorRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/")
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	@GetMapping("authors")
	public List<Author> getAllAuthors() {

		return authorRepository.findAll();
	}

	@GetMapping("author/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable Long id) throws ResourceNotFoundException {

		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + id));

		return ResponseEntity.ok().body(author);
	}

	@PostMapping("author")
	public Author addAuthor(@Valid @RequestBody Author author) {

		return authorRepository.save(author);
		
	}

	@PutMapping("author/{id}")
	public ResponseEntity<Author> updateAuhor(@PathVariable Long id, @Valid @RequestBody Author authorDetails)
			throws ResourceNotFoundException {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + id));

		author.setAuthorName(authorDetails.getAuthorName());
		final Author updateAuthor = authorRepository.save(author);
		return ResponseEntity.ok(updateAuthor);

	}

	@DeleteMapping("author/{id}")
	public Map<String, Boolean> deleteAuthor(@PathVariable Long id) throws ResourceNotFoundException {

		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Author not found for this id :: " + id));
		
		authorRepository.delete(author);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;

	}

	@GetMapping("author/search")
	public ResponseEntity<?> getAuthorByAuthorName(@RequestParam(value = "authorName") String authorName) {

		List<Author> authors = (List<Author>) authorRepository.findAll();

		List<Author> authors1 = new ArrayList<Author>();

		for (Author author : authors) {
			if (author.getAuthorName().contains(authorName)) {
				authors1.add(author);
			}
		}
		return new ResponseEntity<>(authors1, HttpStatus.OK);

	}
}
