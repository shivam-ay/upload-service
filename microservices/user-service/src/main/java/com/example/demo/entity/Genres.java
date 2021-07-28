package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.NoArgsConstructor;

@Entity
@Table(name="genres")
@NoArgsConstructor
public class Genres 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="genre_id")
	private Long genreId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="genre_name")
	private GenreType genreName;
	
	@ManyToMany(mappedBy = "genre")
	@JsonBackReference
	private Set<Books> book;
	
	private enum GenreType{THRILL,ACTION,HORROR,ROMANCE}
}
