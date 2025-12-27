package com.clean.ecotrack.services;

import com.clean.ecotrack.dtos.RecycleRequestDto;

public interface RecycleRequestService {
	
	RecycleRequestDto addRequest(RecycleRequestDto recycleRequestDto, String userId);
	
	RecycleRequestDto rejectRequest(int requestId, String reason);
	
	RecycleRequestDto approveRequest(int requestId);
	
	String setRequestImage(String image, int id);
	
	

}
