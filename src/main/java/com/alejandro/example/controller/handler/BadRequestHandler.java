package com.alejandro.example.controller.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alejandro.example.util.ErrorModel;
import com.alejandro.example.util.ErrorsModel;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorsModel handleValidationExceptions (MethodArgumentNotValidException ex) {
		  Map<String, String> errors = new HashMap<>();
		  ex.getBindingResult().getAllErrors().forEach(e -> {
			  String fieldName = ((FieldError) e).getField();
			  String message = e.getDefaultMessage();
			  errors.put(fieldName, message);
		  });
		  return new ErrorsModel( LocalDateTime.now(),  HttpStatus.BAD_REQUEST, errors);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ErrorModel handleNoSuchElementExceptions (NoSuchElementException ex) {		  
		  return new ErrorModel( LocalDateTime.now(),  HttpStatus.BAD_REQUEST, ex.getMessage());
	}
}
