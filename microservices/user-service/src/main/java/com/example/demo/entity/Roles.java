package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Roles 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;
	
	@Column(name = "role_type")
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public enum Role{ ADMIN,BUYER,SUPPLIER}

	@ManyToMany(fetch = FetchType.LAZY,mappedBy="role")
	@JsonBackReference
	private Set<Users> user;
	
	public Roles(Role role) 
	{
		super();
		this.role = role;
	}
}
