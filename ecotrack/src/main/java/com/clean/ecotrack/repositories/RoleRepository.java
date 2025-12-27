package com.clean.ecotrack.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clean.ecotrack.entites.Role;
import com.clean.ecotrack.enums.AppRole;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	Optional<Role> findByAppRole(AppRole appRole);

}
