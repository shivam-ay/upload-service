package com.example.demo.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name="books")
@NoArgsConstructor
public class Books 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;
	
	@Column(name = "book_title")
	private String bookTitle;
	
	@Column(name = "book_isbn")
	private String isbn;
	
	@Column(name = "published_date")
	private Date publishDate;
	
	@Column(name = "language")
	private String language;
	
	@Column(name = "book_quantity")
	private Integer totalQuantity = 0;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "book_author", referencedColumnName = "author_id")
	private Authors author;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "book_publisher", referencedColumnName = "publisher_id")
	private Publishers publisher;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "book_genre",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "genre_id")
			)
	private Set<Genres> genre;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "book_user_details",
			joinColumns = @JoinColumn(name = "book_user_id"),
			inverseJoinColumns = @JoinColumn(name = "book_id"))
	private List<BookUserData> bookUserData;	
}
