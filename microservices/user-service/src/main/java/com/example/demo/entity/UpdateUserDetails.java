package com.example.demo.entity;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;

@Getter
public class UpdateUserDetails 
{
	@NotBlank(message = "First Name can not be blank")
	private String firstName;
	@NotBlank(message = "Last Name can not be blank")
	private String lastName;
	@Email(message = "Provide a valid mail address")
	private String email;
	@Size(min = 10,max = 10,message = "Phone number must be of 10 digits")
	private String mobileNumber;
	@NotNull(message = "Roles can not be null")
	private Set<Roles> roles;
	@NotNull(message = "Address can not be null")
	private Address address;
}
