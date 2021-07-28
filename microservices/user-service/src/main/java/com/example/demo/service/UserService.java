package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.entity.ForgetPassword;
import com.example.demo.entity.UpdatePassword;
import com.example.demo.entity.UpdateUserDetails;
import com.example.demo.entity.Users;

public interface UserService 
{
	public ResponseEntity<List<Users>> getAllUser();
	public ResponseEntity<Users> addUser(Users user);
	public ResponseEntity<Users> deleteUser(Long userId);
	public ResponseEntity<Users> getByUserId(Long userId);
	public ResponseEntity<Users> updateUser(UpdateUserDetails userDetails,Long userId);
	public ResponseEntity<Users> updatePassword(UpdatePassword updatePassword,Long userId);
	public ResponseEntity<Users> getByUserEmail(String userEmail);
	public ResponseEntity<Users> getByUserMobile(String mobileNumber);
	public ResponseEntity<Users> forgetPassword(ForgetPassword newPassword,String email);
	public ResponseEntity<Users> setAccountState(String email);
}
