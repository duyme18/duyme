package com.hoangducduy.duyme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangducduy.duyme.models.Home;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
}

