package com.clean.ecotrack.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clean.ecotrack.entites.RecycleRequest;

public interface RecycleRequestRepository extends JpaRepository<RecycleRequest, Integer> {
	
	List<RecycleRequest> findByUserId(String userId);

}
