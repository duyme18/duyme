package com.hoangducduy.duyme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangducduy.duyme.models.Librarian;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {

}
