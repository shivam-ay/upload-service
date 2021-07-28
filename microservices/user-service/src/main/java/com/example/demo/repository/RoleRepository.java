package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Roles;

@Repository(value = "roleRepo")
public interface RoleRepository extends JpaRepository<Roles, Long>
{
	public Optional<Roles> findByRole(Roles.Role role);
}
