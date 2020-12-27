package com.hoangducduy.duyme.models;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "category_book")
public class CategoryBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryBookId;

	@Column(name = "category_name")
	private String categoryName;

	@OneToMany(mappedBy = "categoryBook", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Collection<Book> books;

}
