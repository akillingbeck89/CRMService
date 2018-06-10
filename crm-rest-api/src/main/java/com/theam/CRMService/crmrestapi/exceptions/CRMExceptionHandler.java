package com.theam.CRMService.crmrestapi.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.theam.CRMService.crmrestapi.exceptions.internal.CreateCustomerException;
import com.theam.CRMService.crmrestapi.exceptions.internal.CustomerNotFoundException;
import com.theam.CRMService.crmrestapi.exceptions.internal.DeletePhotoException;
import com.theam.CRMService.crmrestapi.exceptions.internal.NoPageContentException;
import com.theam.CRMService.crmrestapi.exceptions.internal.UploadPhotoException;
import com.theam.CRMService.crmrestapi.exceptions.internal.UserNotFoundException;
import com.theam.CRMService.crmrestapi.exceptions.internal.WrongInputException;

@RestController
@ControllerAdvice
public class CRMExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoPageContentException.class)
	public final ResponseEntity<Object> handleNoPageContentException(NoPageContentException ex, WebRequest request){
		
		GlobalExceptionResponse response  = new GlobalExceptionResponse(ex.getMessage(),request.getDescription(false),
				LocalDateTime.now());
		
		return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	public final ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request){
		
		GlobalExceptionResponse response  = new GlobalExceptionResponse(ex.getMessage(),request.getDescription(false),
				LocalDateTime.now());
		
		return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(DeletePhotoException.class)
	public final ResponseEntity<Object> handleDeletePhotoException(DeletePhotoException ex, WebRequest request){
		
		GlobalExceptionResponse response  = new GlobalExceptionResponse(ex.getMessage(),request.getDescription(false),
				LocalDateTime.now());
		
		return new ResponseEntity<Object>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	@ExceptionHandler(UploadPhotoException.class)
	public final ResponseEntity<Object> handleUploadPhotoException(UploadPhotoException ex, WebRequest request){
		
		GlobalExceptionResponse response  = new GlobalExceptionResponse(ex.getMessage(),request.getDescription(false),
				LocalDateTime.now());
		
		return new ResponseEntity<Object>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(CreateCustomerException.class)
	public final ResponseEntity<Object> handleCreateCustomerException(CreateCustomerException ex, WebRequest request){
		
		GlobalExceptionResponse response  = new GlobalExceptionResponse(ex.getMessage(),request.getDescription(false),
				LocalDateTime.now());
		
		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(WrongInputException.class)
	public final ResponseEntity<Object> handleWrongInputException(WrongInputException ex, WebRequest request){
		
		GlobalExceptionResponse response  = new GlobalExceptionResponse(ex.getMessage(),request.getDescription(false),
				LocalDateTime.now());
		
		return new ResponseEntity<Object>(response,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request){
		
		GlobalExceptionResponse response  = new GlobalExceptionResponse(ex.getMessage(),request.getDescription(false),
				LocalDateTime.now());
		
		return new ResponseEntity<Object>(response,HttpStatus.NOT_FOUND);
		
	}
}
