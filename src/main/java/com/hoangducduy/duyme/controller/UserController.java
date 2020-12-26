package com.hoangducduy.duyme.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hoangducduy.duyme.exception.ResourceNotFoundException;
import com.hoangducduy.duyme.models.User;
import com.hoangducduy.duyme.repository.UserRepository;

@RestController
@RequestMapping("/api/auth/v1")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping("users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User này không tồn tại: " + userId));
		return ResponseEntity.ok().body(user);
	}

	@PutMapping("user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User này không tồn tại: " + userId));

		user.setId(userDetails.getId());
		user.setUsername(userDetails.getUsername());
		user.setEmail(userDetails.getEmail());
		user.setPassword(userDetails.getPassword());
		user.setRoles(userDetails.getRoles());

		final User upadteUser = userRepository.save(user);

		return ResponseEntity.ok().body(upadteUser);
	}

	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User này không tồn tại: " + userId));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);

		return response;
	}
}
