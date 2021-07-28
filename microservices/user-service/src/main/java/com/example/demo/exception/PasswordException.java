package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	private String msg;
}
