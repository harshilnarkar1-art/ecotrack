package com.clean.ecotrack.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clean.ecotrack.dtos.WorkShopDto;
import com.clean.ecotrack.entites.WorkShop;

public interface WorkShopService {
	
	WorkShopDto addWorkShop(WorkShopDto workShopDto);
	WorkShopDto updateWorkShop(int id, WorkShopDto workShopDto);
	WorkShopDto getWorkShopById(int id);
	List<WorkShopDto> getAllWorkShops();
	void deleteWorkShops(int id);

}
