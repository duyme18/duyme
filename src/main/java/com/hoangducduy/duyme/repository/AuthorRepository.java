package com.hoangducduy.duyme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangducduy.duyme.models.Author;
import com.hoangducduy.duyme.models.Book;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	List<Book> findBookByAuthorId(Long authorId);
}
