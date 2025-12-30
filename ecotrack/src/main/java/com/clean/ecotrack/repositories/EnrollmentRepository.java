package com.clean.ecotrack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clean.ecotrack.entites.Enrollments;

public interface EnrollmentRepository extends JpaRepository<Enrollments, Integer> {
	
	boolean existsByUserIdAndWorkShopId(String userId, int workShopId);
}
