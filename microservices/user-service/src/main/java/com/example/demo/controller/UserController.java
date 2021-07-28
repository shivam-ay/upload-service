package com.example.demo.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.ForgetPassword;
import com.example.demo.entity.UpdatePassword;
import com.example.demo.entity.UpdateUserDetails;
import com.example.demo.entity.Users;
import com.example.demo.proxy.MailInfo;
import com.example.demo.proxy.MailProxy;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController 
{
	private UserService userService;
	private MailProxy mailProxy;
	
	@Autowired
	public UserController(UserService userService, MailProxy mailProxy)
	{
		super();
		this.userService = userService;
		this.mailProxy = mailProxy;
	}	
	
	@GetMapping("/users")
	public ResponseEntity<List<Users>> getAllUsers()
	{
		return userService.getAllUser();
	}
	
	@PostMapping("/users/signUp")
	public ResponseEntity<Users> addUser(@RequestBody Users user)
	{
		WebMvcLinkBuilder linkToActivateAccount = linkTo(methodOn(UserController.class).activateAccount(user.getEmail()));
		String body ="Hello "+user.getFirstName()+" "+user.getLastName()+"\n"+
					"Welcome to the bookstore\n"+
					"Please access the link below to activate your account\n"+
					linkToActivateAccount.toString()+"\n"+
					"Thanks & Regards\n"+
					"Bookstore\n";
		MailInfo mailInfo = new MailInfo(user.getEmail(),"Welcome to bookstore",body);
		mailProxy.sendSimpleMail(mailInfo);
		return userService.addUser(user);
	}
	
	@GetMapping("/users/set-account-state/{email}")
	public ResponseEntity<Users> activateAccount(@PathVariable String email)
	{
		return userService.setAccountState(email);
	}
	
	@DeleteMapping("/users/{userId}")
	public ResponseEntity<Users> deleteUser(@PathVariable Long userId)
	{
		return userService.deleteUser(userId);
	}
	
	@GetMapping("/users/id/{userId}")
	public ResponseEntity<Users> getById(@PathVariable Long userId)
	{
		return userService.getByUserId(userId);
	}
	
	@PutMapping("/users/{userId}")
	public ResponseEntity<Users> updateUser(@RequestBody UpdateUserDetails userDetails, @PathVariable Long userId)
	{
		return userService.updateUser(userDetails, userId);
	}
	
	@GetMapping("/users/email/{userEmail}")
	public ResponseEntity<Users> getByUserEmail(@PathVariable String userEmail)
	{
		return userService.getByUserEmail(userEmail);
	}
	
	@GetMapping("/users/mobile-number/{mobileNumber}")
	public ResponseEntity<Users> getByMobileNumber(@PathVariable String mobileNumber)
	{
		return userService.getByUserMobile(mobileNumber);
	}
	
	@PutMapping("/users/change-password/{userId}")
	public ResponseEntity<Users> changePassword(@RequestBody UpdatePassword updatePassword,@PathVariable Long userId)
	{
		return userService.updatePassword(updatePassword, userId);
	}
	
	@GetMapping("/users/forget-password/{email}")
	public ResponseEntity<Users> forgetPassword(@PathVariable String email,@RequestBody ForgetPassword newPassword)
	{
		Users user = userService.getByUserEmail(email).getBody();
		if(user != null)
		{
			//Generate Validation Token and send mail with that token;
			String token = "This is an encrypted token";
			WebMvcLinkBuilder linkToResetPassword = linkTo(methodOn(this.getClass()).validateToken(token));
			//Call mail service with above link;
			return ResponseEntity.ok().build();
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@GetMapping("/users/validate-token/{token}")
	public ResponseEntity<Users> validateToken(@PathVariable String token)
	{
		//validate token.
		if(token.equals(token))
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
	}

	@PutMapping("/users/forget-password/{email}/{token}")
	public ResponseEntity<Users> resetPassword(@RequestBody ForgetPassword newPassword,@PathVariable String email,
			@PathVariable String token)
	{
		//Validate token
		return userService.forgetPassword(newPassword, email);
	}
}
