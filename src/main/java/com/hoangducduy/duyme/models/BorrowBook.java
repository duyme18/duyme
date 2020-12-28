package com.hoangducduy.duyme.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "borrow_book")
public class BorrowBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long borrowBookId;

	@Column(name = "borrow_date")
	private LocalDate borrowDate;

	@Column(name = "return_date")
	private LocalDate returnDate;

	private String note;

	@ManyToOne
	@JoinColumn(name="user_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private User user;
}
