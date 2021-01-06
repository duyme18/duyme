package com.hoangducduy.duyme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangducduy.duyme.models.Author;
import com.hoangducduy.duyme.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	List<Book> findByAuthor(Author author);
	
}
