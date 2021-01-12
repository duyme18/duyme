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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "publishing_company")
public class PublishingCompany {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "publishing_company_name")
	private String publishingCompanyName;

	@Column(name = "publishing_company_address")
	private String publishingCompanyAddress;

	@Column(name = "email")
	private String email;

	@Column(name = "information_representative")
	private String informationRepresentative;

	@JsonIgnore
	@OneToMany(mappedBy = "publishingCompany", cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Collection<Book> books;
}
