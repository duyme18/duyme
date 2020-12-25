package com.hoangducduy.duyme.models;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	@NotBlank
	@Size(max = 120)
	private String password;

	@NotBlank
	@Size(max = 120)
	private String address;

	@NotBlank
	private String sex;

	@NotBlank
	@Size(max = 20)
	private String phone;

	@NotBlank
	private LocalDate birthday;

	@NotBlank
	@Size(max = 20)
	private String identification;

	@NotBlank
	@Size(max = 120)
	private String job;

	@NotBlank
	private String picture;

	@OneToMany(targetEntity = Comment.class)
	private List<Comment> comments;

	@OneToMany(targetEntity = BorrowBook.class)
	private List<BorrowBook> borrowBooks;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	public User() {
	}

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

}
