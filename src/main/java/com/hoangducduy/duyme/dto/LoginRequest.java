package com.hoangducduy.duyme.dto;

import lombok.Data;

@Data
public class LoginRequest {

	private String username;
	private String password;

	/**
	 * Create an empty LoginRequest object
	 */
	public LoginRequest() {
		super();
	}

	/**
	 * Create a LoginRequest object with full attributes
	 * 
	 * @param username
	 * @param password
	 */
	public LoginRequest(String username, String password) {
		super();
		this.setUsername(username);
		this.setPassword(password);
	}

}
