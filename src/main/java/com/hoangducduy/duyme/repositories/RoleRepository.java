package com.hoangducduy.duyme.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hoangducduy.duyme.common.ERole;
import com.hoangducduy.duyme.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * Find role by name
	 * 
	 * @param name
	 * @return Role
	 */
	Optional<Role> findByName(ERole name);

}
