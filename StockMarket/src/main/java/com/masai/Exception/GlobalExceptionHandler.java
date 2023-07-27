package com.masai.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(StockException.class)
	public ResponseEntity <ErrorDetails> StockExceptionHandler(StockException stock , WebRequest req) {
		
		return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(),stock.getMessage(), req.getDescription(false)), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> ExceptionHandler(Exception exe, WebRequest req){
		
		return new ResponseEntity<>(new ErrorDetails(LocalDateTime.now(),exe.getMessage(),req.getDescription(false)),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> MethodExceptionHandler(MethodArgumentNotValidException exe, WebRequest req){
		
		return new  ResponseEntity<>(new ErrorDetails(LocalDateTime.now(),exe.getMessage(),req.getDescription(false)), HttpStatus.BAD_REQUEST);
	}

}
