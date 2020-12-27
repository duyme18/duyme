package com.hoangducduy.duyme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangducduy.duyme.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	
	Optional<Author> findById(Long id);
	
}
