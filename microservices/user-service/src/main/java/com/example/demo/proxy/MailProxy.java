package com.example.demo.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "email-service")
public interface MailProxy 
{
	@PostMapping("/api/send-simple-mail")
	public ResponseEntity<MailInfo> sendSimpleMail(@RequestBody MailInfo mailInfo);
	
	@PostMapping("/api/send-mail-with-attachment")
	public ResponseEntity<MailInfoWithAttachment> senMailWithAttachment(@RequestBody MailInfoWithAttachment mailInfo);
}
