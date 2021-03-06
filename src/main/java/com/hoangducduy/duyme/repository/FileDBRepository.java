package com.hoangducduy.duyme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangducduy.duyme.models.Book;
import com.hoangducduy.duyme.models.FileDB;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {
	
	List<FileDB> findByBook(Book book);
}
