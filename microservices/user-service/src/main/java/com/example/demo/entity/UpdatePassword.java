package com.example.demo.entity;

import lombok.Getter;

@Getter
public class UpdatePassword 
{
	private String oldPassword;
	private String newPassword;
	private String reWriteNewPassword;
}
