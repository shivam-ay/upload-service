package com.example.demo.proxy;

import java.util.List;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MailInfoWithAttachment 
{
	@Email
	private String email;
	private String subject;
	private String body;
	private List<String> attachments;
}
