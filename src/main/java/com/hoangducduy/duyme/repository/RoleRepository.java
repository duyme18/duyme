package com.hoangducduy.duyme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangducduy.duyme.models.ERole;
import com.hoangducduy.duyme.models.Role;


@Repository
public interface RoleRepository extends JpaRepository<com.hoangducduy.duyme.models.Role, Long> {
	Optional<Role> findByName(ERole name);
}
