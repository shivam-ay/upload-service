package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Users;

@Repository(value = "userRepo")
public interface UserRepository extends JpaRepository<Users, Long>
{
	public Optional<Users> findByEmail(String email);
	public Optional<Users> findByMobileNumber(String mobileNumber);
}
