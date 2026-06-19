package com.smartshop.order_service.exception;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorResponse> RuntimeExceptionHandler(RuntimeException ex){
		
ErrorResponse error=new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(),LocalDateTime.now());
		
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex){
		
		ErrorResponse error=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "SomeThing Went Wrong", LocalDateTime.now());
		
		return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
	    String message = ex.getBindingResult().getFieldErrors().stream()
	        .map(e -> e.getField() + ": " + e.getDefaultMessage())
	        .collect(Collectors.joining(", "));
	    ErrorResponse error = new ErrorResponse(
	        HttpStatus.BAD_REQUEST.value(), message, LocalDateTime.now());
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
		
		
	}


