package example.test.springbootmultidb.contacts.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import example.test.springbootmultidb.contacts.exception.handler.PreValidationError;
import org.springframework.http.HttpStatus;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.List;

public class AppError {

	private int statusCode;
	
	private HttpStatus httpStatus;
	
	private String referenceId;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;
	
	private String message;
	
	private List<PreValidationError> preValidationErrors;
	
	private ValidationErrors validationErrors;
	
	public AppError() {
		this.timestamp = LocalDateTime.now();
		addRequestID();
	}

	public AppError(HttpStatus httpStatus, String message) {
		this.httpStatus = httpStatus;
		this.message = message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PreValidationError> getPreValidationErrors() {
		return preValidationErrors;
	}

	public void setPreValidationErrors(List<PreValidationError> preValidationErrors) {
		this.preValidationErrors = preValidationErrors;
	}

	public ValidationErrors getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(ValidationErrors validationErrors) {
		this.validationErrors = validationErrors;
	}

	private void addRequestID() {
		String hostName = "UNK";
		
		try {
			String host = InetAddress.getLocalHost().getHostName();
			hostName = host.substring(4, 7);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
		}
		//this.referenceId = String.format("%1$s - %2$s", ThreadContext.get("ReferenceID"), hostName);
	}
}
