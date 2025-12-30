package com.clean.ecotrack.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clean.ecotrack.dtos.EnrollmentsDto;
import com.clean.ecotrack.services.EnrollmentService;
import com.clean.ecotrack.services.UserService;
import com.clean.ecotrack.services.WorkShopService;

@RestController
@RequestMapping("enroll")
public class EnrollmentController {
	
	@Autowired
	private WorkShopService workShopService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	
	@PostMapping("/{userId}/{workshopId}")
	public ResponseEntity<EnrollmentsDto> addEnroll(@PathVariable String userId,@PathVariable int workshopId){
		
		return ResponseEntity.ok(enrollmentService.enroll(userId, workshopId));
	}

}
