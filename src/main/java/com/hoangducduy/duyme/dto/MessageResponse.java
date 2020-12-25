package com.hoangducduy.duyme.dto;

import lombok.Data;

@Data
public class MessageResponse {

	private String message;

	public MessageResponse(String message) {
		this.setMessage(message);
	}

}
