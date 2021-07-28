package com.example.demo.entity;

import java.util.List;

import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
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
