package com.clean.ecotrack.services;

import com.clean.ecotrack.dtos.EnrollmentsDto;

public interface EnrollmentService {
	
	EnrollmentsDto enroll(String userId,int workShopId);
	
	
}
