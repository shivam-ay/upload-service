package com.example.demo.proxy;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MailInfo 
{
	@Email(message = "Please provide a valid mail address")
	private String email;
	private String subject;
	private String body;
}
