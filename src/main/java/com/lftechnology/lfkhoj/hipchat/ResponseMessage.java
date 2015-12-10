package com.lftechnology.lfkhoj.hipchat;

import lombok.Data;

/**
 * DTO for ResponseMessage, message format that hipchat accepts.
 *
 *
 */
@Data
public class ResponseMessage {
	private String color;
	private String message;
	private String notify;
	private String message_format;
	
	public ResponseMessage() {
		this.color = "Green";
		this.notify = "false";
		this.message_format = "text";
	}
}
