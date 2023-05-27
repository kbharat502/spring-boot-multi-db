package example.test.springbootmultidb.contacts.exception.controller.advice;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ExceptionResponse {

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private LocalDateTime timestamp;
	
	private String message;
	
	private String details;

	public ExceptionResponse(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}
	
	
}
