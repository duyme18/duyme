package com.hoangducduy.duyme.models;

import java.time.LocalDate;
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
@Table(name = "employee")
public class Librarian {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	@Column(name = "firs_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "birthday")
	private LocalDate birthday;
	
	@Column(name = "phone")
	private String phone;
	
	@OneToMany(targetEntity = BorrowBook.class)
	private List<BorrowBook> borrowBooks;
	
}
