package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_user_data")
@NoArgsConstructor
public class BookUserData 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_user_id")
	private Long bookUserDataId;
	
	@Column(name = "book_price")
	private Double bookPrice;
	
	@Column(name = "date_added")
	private Date dateAdded;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@Column(name = "book_image")
	private String bookImage;
	
	@Column(name = "book_description")
	private String bookDescription;
	
	@Column(name = "book_condition")
	@Enumerated(EnumType.STRING)
	private Condition condition;
	
	@ManyToOne
	@JoinColumn(name = "user_id",referencedColumnName = "user_id")
	private Users user;
	
	private enum Condition{NEW,EXCELLENT,AVERAGE,GOOD,POOR}

}
