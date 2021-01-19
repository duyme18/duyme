package com.hoangducduy.duyme.controller;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
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
import com.hoangducduy.duyme.models.Home;
import com.hoangducduy.duyme.payload.response.MessageResponse;
import com.hoangducduy.duyme.repository.HomeRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth/")
public class HomeController {

	@Autowired
	HomeRepository homeRepository;

	@Autowired
	ServletContext context;

	@GetMapping("homes")
	public List<Home> getAllarticles() {
		System.out.println("Get all homes...");

		List<Home> homes = new ArrayList<>();
		homeRepository.findAll().forEach(homes::add);

		return homes;
	}

	@GetMapping("getAll")
	public ResponseEntity<List<String>> getAll() {
		List<String> listHome = new ArrayList<String>();
		String filesPath = context.getRealPath("/Images");
		File fileFolder = new File(filesPath);
		if (fileFolder != null) {
			for (File file : fileFolder.listFiles()) {
				if (!file.isDirectory()) {
					String encodeBase64 = null;
					try {
						String extension = FilenameUtils.getExtension(file.getName());
						FileInputStream fileInputStream = new FileInputStream(file);
						byte[] bytes = new byte[(int) file.length()];
						fileInputStream.read(bytes);
						encodeBase64 = Base64.getEncoder().encodeToString(bytes);
						listHome.add("data:image/" + extension + ";base64," + encodeBase64);
						fileInputStream.close();

					} catch (Exception e) {

					}
				}
			}
		}
		return new ResponseEntity<List<String>>(listHome, HttpStatus.OK);
	}

	@GetMapping("homes/{id}")
	public ResponseEntity<Home> getHomeById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Home home = homeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Home not found for this id :: " + id));
		return ResponseEntity.ok().body(home);
	}

	@GetMapping(path = "/ImgHomes/{id}")
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception {
		Home home = homeRepository.findById(id).get();
		return Files.readAllBytes(Paths.get(context.getRealPath("/Images/") + home.getFileName()));
	}

	@PostMapping("homes")
	public ResponseEntity<MessageResponse> createHome(@RequestParam("file") MultipartFile file,
			@RequestParam("home") String home) throws JsonParseException, JsonMappingException, Exception {
		System.out.println("Ok .............");
		Home home1 = new ObjectMapper().readValue(home, Home.class);
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

		home1.setFileName(newFileName);
		Home home2 = homeRepository.save(home1);
		if (home2 != null) {
			return new ResponseEntity<MessageResponse>(new MessageResponse(""), HttpStatus.OK);
		} else {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Home not saved"), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("homes/{id}")
	public ResponseEntity<Home> updateHome(@PathVariable("id") Long id, @RequestBody Home homeDetails)
			throws ResourceNotFoundException {
		System.out.println("Update Home with ID = " + id + "...");
		Home home = homeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Home not found for this id :: " + id));
		
		home.setAddress(homeDetails.getAddress());
		home.setCreateDate(homeDetails.getCreateDate());
		home.setFileName(homeDetails.getFileName());
		
		final Home updateHome = homeRepository.save(home);
		
		return ResponseEntity.ok(updateHome);
	}

	@DeleteMapping("homes/{id}")
	public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
		Home home = homeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Home not found  id :: " + id));
		homeRepository.delete(home);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@DeleteMapping("homes/delete")
	public ResponseEntity<String> deleteAllarticles() {
		System.out.println("Delete All homes...");
		homeRepository.deleteAll();
		return new ResponseEntity<>("All homes have been deleted!", HttpStatus.OK);
	}
}
