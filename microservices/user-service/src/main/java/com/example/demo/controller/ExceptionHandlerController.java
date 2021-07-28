package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.exception.ErrorResponse;
import com.example.demo.exception.PasswordException;
import com.example.demo.exception.UserNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController 
{
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException exception)
	{
		ErrorResponse response = new ErrorResponse(exception.getMsg(),LocalDateTime.now(),HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorResponse>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handlePasswordException(PasswordException exception)
	{
		ErrorResponse response = new ErrorResponse(exception.getMsg(),LocalDateTime.now(),HttpStatus.FORBIDDEN.value());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.FORBIDDEN);
	}
}
