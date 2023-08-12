package com.alphaware.documentmaster.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CentralExceptionHandler {

	@ExceptionHandler(FileNotSelectException.class)
	public ResponseEntity<Map<String, String>> handlerFileNotSelectException( FileNotSelectException exception){
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", exception.getMessage());
		return ResponseEntity.badRequest().body(errorResponse);
	}
	
	@ExceptionHandler(InvalidUuidException.class)
	public ResponseEntity<Map<String, String>> handlerInvalidUuidException( InvalidUuidException exception){
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", exception.getMessage());
		return ResponseEntity.badRequest().body(errorResponse);
	}
	
	@ExceptionHandler(FileIsInvalidException.class)
	public ResponseEntity<Map<String, String>> handlerFileIsInvalidUuidException( FileIsInvalidException exception){
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", exception.getMessage());
		return ResponseEntity.badRequest().body(errorResponse);
	}
}
