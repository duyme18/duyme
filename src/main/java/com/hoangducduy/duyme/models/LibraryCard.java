package com.hoangducduy.duyme.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "library_card")
public class LibraryCard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long libraryCardId;

	@Column(name = "start_date")
	private LocalDate startDate;

	@Column(name = "end_date")
	private LocalDate endDate;

	@Column(name = "note")
	private String note;

}
