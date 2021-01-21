package com.hoangducduy.duyme.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangducduy.duyme.exception.ResourceNotFoundException;
import com.hoangducduy.duyme.models.User;
import com.hoangducduy.duyme.payload.response.MessageResponse;
import com.hoangducduy.duyme.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/v1")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	ServletContext context;

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

//	@PutMapping("user/{id}")
//	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
//			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
//		User user = userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User này không tồn tại: " + userId));
//
//		user.setId(userDetails.getId());
//		user.setUsername(userDetails.getUsername());
//		user.setEmail(userDetails.getEmail());
//		user.setPassword(userDetails.getPassword());
//		user.setRoles(userDetails.getRoles());
//
//		final User upadteUser = userRepository.save(user);
//
//		return ResponseEntity.ok().body(upadteUser);
//	}

	@PutMapping("user/{id}")
	public ResponseEntity<MessageResponse> updateUser(@PathVariable("id") Long id,
			@RequestParam("file") MultipartFile file, @RequestParam("user") String user)
			throws JsonParseException, JsonMappingException, Exception {
		System.out.println("Update User with ID = " + id + "...");
		User user1 = new ObjectMapper().readValue(user, User.class);
		boolean isExists = new File(context.getRealPath("/Images/")).exists();
		if (!isExists) {
			new File(context.getRealPath("/Images/")).mkdir();
			System.out.println("mk dir.............");
		}
		String filename = file.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(filename) + "." + FilenameUtils.getExtension(filename);
		File serverFile = new File(context.getRealPath("/Images/" + File.separator + newFileName));
		try {
			System.out.println("Image");
			FileUtils.writeByteArrayToFile(serverFile, file.getBytes());

		} catch (Exception e) {
			e.printStackTrace();
		}

		user1.setFileName(newFileName);
		User user2 = userRepository.save(user1);

		if (user2 != null) {
			return new ResponseEntity<MessageResponse>(new MessageResponse(""), HttpStatus.OK);
		} else {
			return new ResponseEntity<MessageResponse>(new MessageResponse("User not saved"), HttpStatus.BAD_REQUEST);
		}
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
