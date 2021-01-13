package com.hoangducduy.duyme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoangducduy.duyme.exception.ResourceNotFoundException;
import com.hoangducduy.duyme.models.Librarian;
import com.hoangducduy.duyme.repository.LibrarianRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/")
public class LibrarianController {

	@Autowired
	private LibrarianRepository librarianRepository;

	@GetMapping("librarians")
	public List<Librarian> getAllLibrarians() {
		return librarianRepository.findAll();
	}

	@GetMapping("librarian/{id}")
	public ResponseEntity<Librarian> getLibrarianById(@PathVariable Long id) throws ResourceNotFoundException {

		Librarian librarian = librarianRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Librarian not found for this id :: " + id));

		return ResponseEntity.ok().body(librarian);
	}

	@PostMapping("librarian")
	public Librarian addLibrarian(@Valid @RequestBody Librarian librarian) {

		return librarianRepository.save(librarian);

	}

	@PutMapping("librarian/{id}")
	public ResponseEntity<Librarian> updateLibrarian(@PathVariable Long id, @RequestBody Librarian librarianDetails)
			throws ResourceNotFoundException {

		Librarian librarian = librarianRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Librarian not found for this id :: " + id));

		librarian.setFirstName(librarianDetails.getFirstName());
		librarian.setLastName(librarianDetails.getLastName());
		librarian.setBirthday(librarianDetails.getBirthday());
		librarian.setAddress(librarianDetails.getAddress());
		librarian.setPhone(librarianDetails.getPhone());

		final Librarian updateLibrarian = librarianRepository.save(librarian);

		return ResponseEntity.ok(updateLibrarian);

	}

	@DeleteMapping("librarian/{id}")
	public Map<String, Boolean> deleteLibrarian(@PathVariable Long id) throws ResourceNotFoundException {

		Librarian librarian = librarianRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Librarian not found for this id :: " + id));

		librarianRepository.delete(librarian);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;

	}
}
