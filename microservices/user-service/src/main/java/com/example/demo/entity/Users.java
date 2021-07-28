package com.example.demo.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
public class Users
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "first_name")
	@NotBlank(message = "First Name can not be empty")
	private String firstName;
	
	@Column(name = "last_name")
	@NotBlank(message = "Last Name can not be empty")
	private String lastName;
	
	@Column(name = "email")
	@Email(message = "Enter a valid email id")
	private String email;
	
	@Column(name = "password")
	@NotNull(message = "Please Enter a valid password")
	@Size(min = 6,message = "Password must be of atleast 6 characters")
	private String password;
	
	@Column(name = "mobile_number")
	@Size(min = 10, max = 10,message="Mobile number must be of 10 digits")
	private String mobileNumber;
	
	@Column(name = "is_active")
	private boolean isActive = false;
	
	@ManyToMany
	@JoinTable(
			name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	@NotNull(message = "Role can not be null")
	private Set<Roles> role;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id", referencedColumnName="address_id")
	@NotNull(message = "Address can not be null")
	private Address address;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	@JsonBackReference
	private List<BookUserData> userBooks;

	public Users(String firstName, String lastName, String email, String password, String mobileNumber, Set<Roles> role,
			Address address) 
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.role = role;
		this.address = address;
	}
}
