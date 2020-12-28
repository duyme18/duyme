package com.hoangducduy.duyme.models;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
	
	@Column(name = "rent_const")
	private Double rentConst;

	@Column(name = "publishing_year")
	private LocalDate publishingYear;

	@JsonIgnore
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Collection<Comment> comments;

	@ManyToOne
	@JoinColumn(name="category_book_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private CategoryBook categoryBook;

	@ManyToOne
	@JoinColumn(name="author_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Author author;

	@ManyToOne
	@JoinColumn(name = "publishing_company_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private PublishingCompany publishingCompany;
	
	public Book() {
		
	}
}
