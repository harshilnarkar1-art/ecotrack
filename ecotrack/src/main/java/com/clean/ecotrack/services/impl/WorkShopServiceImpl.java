package com.clean.ecotrack.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clean.ecotrack.dtos.WorkShopDto;
import com.clean.ecotrack.entites.WorkShop;
import com.clean.ecotrack.exceptions.NotFoundException;
import com.clean.ecotrack.repositories.WorkShopRepository;
import com.clean.ecotrack.services.WorkShopService;



@Service
public class WorkShopServiceImpl implements WorkShopService  {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private WorkShopRepository workShopRepository;

	@Override
	public WorkShopDto addWorkShop(WorkShopDto workShopDto) {
		WorkShop savedWorkShop = workShopRepository.save(modelMapper.map(workShopDto, WorkShop.class));
		
		return modelMapper.map(savedWorkShop, WorkShopDto.class);
	}

	@Override
	public WorkShopDto updateWorkShop(int id, WorkShopDto workShopDto) {
		workShopRepository.findById(id).orElseThrow(() -> new NotFoundException("Workshop Not Found"));
		WorkShop workShop = modelMapper.map(workShopDto, WorkShop.class );
		workShop.setId(id);
		
		WorkShop savedWorkShop = workShopRepository.save(workShop);

		return modelMapper.map(savedWorkShop,WorkShopDto.class);
	}

	@Override
	public WorkShopDto getWorkShopById(int id) {
		WorkShop workShop = workShopRepository.findById(id).orElseThrow(() -> new NotFoundException("Workshop Not Found"));

		return modelMapper.map(workShop, WorkShopDto.class);
	}

	@Override
	public List<WorkShopDto> getAllWorkShops() {
		
		return workShopRepository.findAll().stream().map(w ->  modelMapper.map(w, WorkShopDto.class)).toList();
	}

	@Override
	public void deleteWorkShops(int id) {
		WorkShop workShop = workShopRepository.findById(id).orElseThrow(() -> new NotFoundException("Workshop Not Found"));

		workShopRepository.delete(workShop);
		
	}

}
