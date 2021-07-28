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
@Table(name="authors")
@NoArgsConstructor
public class Authors 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="author_id")
	private Long authorId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="author_description")
	private String authorDescription;
	
	@OneToMany(mappedBy="author",fetch=FetchType.LAZY)
	@JsonBackReference
	private Set<Books> book;
}
