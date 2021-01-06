package com.hoangducduy.duyme.models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentId;

	@Lob
	@Column(name = "content")
	private String content;

	@Column(name = "comment_date")
	private LocalDateTime commentDate;

	@Column(name = "is_edit")
	private Boolean isEdit;
	
    @ManyToOne
    @JoinColumn(name = "book_id")
    @EqualsAndHashCode.Exclude
	@ToString.Exclude
    private Book book;
    
	@ManyToOne
	@JoinColumn(name = "user_id")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private User user;
}
