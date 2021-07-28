package com.example.demo.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MailInfo;
import com.example.demo.entity.MailInfoWithAttachment;

@Service(value = "mailSenderService")
public class MailSenderServiceImpl implements MailSenderService
{
	@Autowired
	private JavaMailSender mailSender; 
	
	@Override
	public ResponseEntity<MailInfo> sendSimpleEmail(MailInfo mailInfo) 
	{
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("maitrap.io@email.com");
		message.setTo(mailInfo.getEmail());
		message.setText(mailInfo.getBody());
		message.setSubject(mailInfo.getSubject());
		 
		try
		{
			mailSender.send(message);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@Override
	public ResponseEntity<MailInfoWithAttachment> sendMailWithAttachment(MailInfoWithAttachment mailInfo) throws MessagingException
	{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
		
		mimeMessageHelper.setFrom("maitrap.io@email.com");
		mimeMessageHelper.setTo(mailInfo.getEmail());
		mimeMessageHelper.setText(mailInfo.getBody());
		mimeMessageHelper.setSubject(mailInfo.getSubject());
		
		for(String attachment:mailInfo.getAttachments())
		{
			FileSystemResource fileSystem = new FileSystemResource(new File(attachment));
			mimeMessageHelper.addAttachment(fileSystem.getFilename(),fileSystem);
		}
		
		mailSender.send(mimeMessage);
		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
