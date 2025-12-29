package com.clean.ecotrack.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clean.ecotrack.dtos.WorkShopDto;
import com.clean.ecotrack.services.WorkShopService;

@RestController
@RequestMapping("/workshops")
public class WorkShopController {
	
	@Autowired
	private WorkShopService workShopService;
	
	@PostMapping
	public ResponseEntity<WorkShopDto> createWorkShop(@RequestBody WorkShopDto workShopDto){
		return new ResponseEntity<WorkShopDto>(workShopService.addWorkShop(workShopDto),HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<WorkShopDto>> getAllWorkShops(){
		return ResponseEntity.ok(workShopService.getAllWorkShops());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<WorkShopDto> getWorkShopById(@PathVariable int id){
		return ResponseEntity.ok(workShopService.getWorkShopById(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteWorkShpByid(@PathVariable int id){
		workShopService.deleteWorkShops(id);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("Message ", id+ "Workshop Deleted");
		return ResponseEntity.ok(map);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<WorkShopDto> updateWorkShop(@RequestBody WorkShopDto workShopDto, @PathVariable int id){
		return ResponseEntity.ok(workShopService.updateWorkShop(id, workShopDto));
	}
	
	
 
}
