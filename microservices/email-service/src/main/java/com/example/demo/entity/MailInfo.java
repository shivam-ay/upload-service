package com.example.demo.entity;

import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MailInfo 
{
	@Email(message = "Please provide a valid mail address")
	private String email;
	private String subject;
	private String body;
}
