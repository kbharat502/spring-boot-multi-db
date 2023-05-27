package example.test.springbootmultidb.contacts.exception.controller.advice;

import example.test.springbootmultidb.contacts.exception.ContactCreationException;
import example.test.springbootmultidb.contacts.exception.ContactNotFoundException;
import example.test.springbootmultidb.contacts.exception.EmployeeCreationException;
import example.test.springbootmultidb.contacts.exception.EmployeeNotFoundException;
import example.test.springbootmultidb.contacts.exception.ProjectNotFoundException;
import example.test.springbootmultidb.contacts.exception.model.AppError;
import example.test.springbootmultidb.contacts.exception.model.FieldValidationError;
import example.test.springbootmultidb.contacts.exception.model.RequestError;
import example.test.springbootmultidb.contacts.exception.model.ValidationErrors;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		BindingResult bindingResult = ex.getBindingResult();
		
		List<FieldValidationError> fldErrors = bindingResult.getFieldErrors().stream()
				.map(fError -> {
					FieldValidationError fe = new FieldValidationError();
					fe.setFieldName(fError.getField());
					fe.setMessage(fError.getDefaultMessage());
					return fe;
				}).collect(Collectors.toList());
		
		List<RequestError> requestErrors = bindingResult.getGlobalErrors().stream()
		.map(globalError -> {
			RequestError reqError = new RequestError();
			reqError.setMessage(globalError.getDefaultMessage());
			return reqError;
		}).collect(Collectors.toList());
		
		ValidationErrors ve = new ValidationErrors();
		ve.setFieldErrors(fldErrors);
		ve.setRequestErrors(requestErrors);
		
		AppError appError = new AppError(HttpStatus.BAD_REQUEST, "ValidationErrors exists. Please correct.");
		appError.setValidationErrors(ve);
		appError.setReferenceId(ThreadContext.get("ReferenceID"));
		appError.setTimestamp(LocalDateTime.now());
		appError.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		
		return new ResponseEntity<>(appError, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(ContactNotFoundException ex, WebRequest request) {
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), ThreadContext.get("ReferenceID")), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(ContactCreationException.class)
	public ResponseEntity<ExceptionResponse> handler(ContactCreationException ex, WebRequest request) {
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), ThreadContext.get("ReferenceID")), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(EmployeeNotFoundException ex, WebRequest request) {
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), ThreadContext.get("ReferenceID")), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EmployeeCreationException.class)
	public ResponseEntity<ExceptionResponse> handler(EmployeeCreationException ex, WebRequest request) {
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), ThreadContext.get("ReferenceID")), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(ProjectNotFoundException ex, WebRequest request) {
		return new ResponseEntity<ExceptionResponse>(new ExceptionResponse(LocalDateTime.now(), ex.getMessage(), ThreadContext.get("ReferenceID")), HttpStatus.NOT_FOUND);
	}
}
