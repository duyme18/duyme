package com.hoangducduy.duyme.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "book")
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookId;

	@Column(name = "book_name")
	private String bookName;

	@Column(name = "translator")
	private String translator;

	@Column(name = "book_amount")
	private Integer bookAmount;

	@Column(name = "publishing_year")
	private LocalDate publishingYear;

	@OneToMany(targetEntity = Comment.class)
	private List<Comment> comments;

	@ManyToOne
	@JoinColumn(name = "book_category_book")
	private CategoryBook categoryBook;

	@ManyToOne
	@JoinColumn(name = "book_author")
	private Author author;

	@ManyToOne
	@JoinColumn(name = "book_publishing_company")
	private PublishingCompany publishingCompany;
	
	public Book() {
		
	}
}
