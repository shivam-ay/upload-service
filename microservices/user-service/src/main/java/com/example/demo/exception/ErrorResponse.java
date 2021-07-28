package com.example.demo.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse 
{
	private String msg;
	private LocalDateTime timestamp;
	private int statusCode;
}
