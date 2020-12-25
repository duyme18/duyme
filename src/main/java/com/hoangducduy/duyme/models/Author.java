package com.hoangducduy.duyme.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "author")
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long authorId;

	@Column(name = "author_name")
	private String authorName;

	@Column(name = "author_picture")
	private String authorPicture;

	@OneToMany(targetEntity = Book.class)
	private List<Book> books;
}
