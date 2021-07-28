package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.NoArgsConstructor;

@Entity
@Table(name="publishers")
@NoArgsConstructor
public class Publishers 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="publisher_id")
	private Long prublisherId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="publisher_description")
	private String publisherDescription;
	
	@OneToMany(mappedBy="publisher",fetch=FetchType.LAZY)
	@JsonBackReference
	private Set<Books> book;
}
