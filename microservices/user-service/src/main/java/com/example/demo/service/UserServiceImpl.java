package com.example.demo.service;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entity.ForgetPassword;
import com.example.demo.entity.Roles;
import com.example.demo.entity.UpdatePassword;
import com.example.demo.entity.UpdateUserDetails;
import com.example.demo.entity.Users;
import com.example.demo.exception.PasswordException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service(value = "userService")
public class UserServiceImpl implements UserService
{
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo, RoleRepository roleRepo)
	{
		super();
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
	}
	
	@Override
	public ResponseEntity<List<Users>> getAllUser() 
	{
		return ResponseEntity.ok().body(userRepo.findAll());
	}

	//Add a new user
	@Override
	public ResponseEntity<Users> addUser(Users user) 
	{
		Set<Roles> userRoles = new HashSet<Roles>();
		for(Roles role:user.getRole())
		{
			Optional<Roles> userRole = roleRepo.findByRole(role.getRole());
			if(userRole.isPresent())
				userRoles.add(userRole.get());
			else
				return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).build();//new InvalidRoleException();
		}
		user.setRole(userRoles);
		user.setActive(false);
		userRepo.save(user);
		URI location = ServletUriComponentsBuilder
						.fromCurrentRequest()
						.path("/{id}")
						.buildAndExpand(user.getUserId())
						.toUri();
		return ResponseEntity.created(location).build();
	}

	@Override
	public ResponseEntity<Users> deleteUser(Long userId)
	{
		Optional<Users> user = userRepo.findById(userId);
		if(user.isPresent())
		{
			userRepo.delete(user.get());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		else
			throw new UserNotFoundException("No user found with id : "+userId);
	}

	@Override
	public ResponseEntity<Users> getByUserId(Long userId) 
	{
		Optional<Users> user = userRepo.findById(userId);
		if(user.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(user.get());
		else
			throw new UserNotFoundException("No user found with id : "+userId);
	}

	@Override
	public ResponseEntity<Users> updateUser(UpdateUserDetails userDetails,Long userId)
	{
		Optional<Users> user = userRepo.findById(userId);
		if(user.isPresent())
		{
			user.get().setFirstName(userDetails.getFirstName());
			user.get().setLastName(userDetails.getLastName());
			user.get().setEmail(userDetails.getEmail());
			user.get().setAddress(userDetails.getAddress());
			user.get().setMobileNumber(userDetails.getMobileNumber());
			user.get().setRole(userDetails.getRoles());
			userRepo.save(user.get());
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{email}")
					.buildAndExpand(user.get().getEmail())
					.toUri();
			return ResponseEntity.status(HttpStatus.OK).location(location).build();
		}
		else
			throw new UserNotFoundException("No user exists with user-id : "+userId);
	}

	@Override
	public ResponseEntity<Users> getByUserEmail(String userEmail) 
	{
		Optional<Users> user = userRepo.findByEmail(userEmail);
		if(user.isPresent())
			return ResponseEntity.ok().body(user.get());
		else
			throw new UserNotFoundException("No user exists with email id : "+userEmail);
	}

	@Override
	public ResponseEntity<Users> getByUserMobile(String mobileNumber)
	{
		Optional<Users> user = userRepo.findByMobileNumber(mobileNumber);
		if(user.isPresent())
			return ResponseEntity.ok().body(user.get());
		else
			throw new UserNotFoundException("No user exists with contact number : "+mobileNumber);
	}

	@Override
	public ResponseEntity<Users> updatePassword(UpdatePassword updatePassword, Long userId) 
	{
		Optional<Users> user = userRepo.findById(userId);
		if(user.isPresent())
		{
			if(user.get().getPassword() == updatePassword.getOldPassword())
			{
				if(updatePassword.getNewPassword().equals(updatePassword.getReWriteNewPassword()))
				{
					//Encrypt password
					String encryptedPassword = updatePassword.getNewPassword();
					user.get().setPassword(encryptedPassword);
				}
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			else
				throw new PasswordException("Old and New passwords do not match");
		}
		else
			throw new UserNotFoundException("No user exists with user-id : "+userId);
	}

	@Override
	public ResponseEntity<Users> forgetPassword(ForgetPassword newPassword,String email) 
	{
		//Use user.email to call email service and validate user then reset password.
		if(newPassword.getNewPassword().equals(newPassword.getReWriteNewPassword()))
		{
			Optional<Users> user = userRepo.findByEmail(email);
			if(user.isPresent())
			{
				//Encrypt Password
				String encryptedPassword = newPassword.getNewPassword();
				user.get().setPassword(encryptedPassword);
				return ResponseEntity.status(HttpStatus.OK).build();
			}
			else
				throw new UserNotFoundException("No user exists with email : "+email);
		}
		else
			throw new PasswordException("New passwords do not match");
	}

	@Override
	public ResponseEntity<Users> setAccountState(String email) 
	{
		Optional<Users> user = userRepo.findByEmail(email);
		if(user.isPresent())
		{
			user.get().setActive(!user.get().isActive());
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		else
			throw new UserNotFoundException("No user exists with email: "+email);
	}
	
}
