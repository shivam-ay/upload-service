package com.example.demo.service;

import javax.mail.MessagingException;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.MailInfo;
import com.example.demo.entity.MailInfoWithAttachment;

public interface MailSenderService 
{
	public ResponseEntity<MailInfo> sendSimpleEmail(MailInfo mailInfo);

	public ResponseEntity<MailInfoWithAttachment> sendMailWithAttachment(MailInfoWithAttachment mailInfo) throws MessagingException;
}
