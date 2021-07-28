package com.example.demo.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.MailInfo;
import com.example.demo.entity.MailInfoWithAttachment;
import com.example.demo.service.MailSenderService;

@RestController
@RequestMapping("/api")
public class MailSenderController 
{
	@Autowired
	private MailSenderService mailSenderService;
	
	@PostMapping("/send-simple-mail")
	public ResponseEntity<MailInfo> sendSimpleMail(@RequestBody MailInfo mailInfo)
	{
		return mailSenderService.sendSimpleEmail(mailInfo);
	}
	
	@PostMapping("/send-mail-with-attachment")
	public ResponseEntity<MailInfoWithAttachment> sendMailWithAttactments(@RequestBody MailInfoWithAttachment mailInfo) throws MessagingException
	{
		return mailSenderService.sendMailWithAttachment(mailInfo);
	}
}
