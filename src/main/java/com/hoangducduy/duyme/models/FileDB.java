package com.hoangducduy.duyme.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "files")
public class FileDB {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String name;

	private String type;

	@Lob
	private byte[] data;

	public FileDB() {
	}

	public FileDB(String name, String type, byte[] data) {
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public FileDB(String name, String type, byte[] data, Book book) {
		this.name = name;
		this.type = type;
		this.data = data;
		this.book = book;
	}

	@ManyToOne
	@JoinColumn(name = "book_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Book book;
}
