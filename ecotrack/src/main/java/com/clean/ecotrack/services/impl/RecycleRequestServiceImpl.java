package com.clean.ecotrack.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clean.ecotrack.dtos.RecycleRequestDto;
import com.clean.ecotrack.entites.RecycleRequest;
import com.clean.ecotrack.entites.User;
import com.clean.ecotrack.enums.RequestStatus;
import com.clean.ecotrack.exceptions.NotFoundException;
import com.clean.ecotrack.repositories.RecycleRequestRepository;
import com.clean.ecotrack.repositories.UserRepository;
import com.clean.ecotrack.services.RecycleRequestService;

@Service
public class RecycleRequestServiceImpl implements RecycleRequestService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RecycleRequestRepository recycleRequestRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public RecycleRequestDto addRequest(RecycleRequestDto recycleRequestDto, String userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User Not Found"));
		RecycleRequest request = modelMapper.map(recycleRequestDto, RecycleRequest.class);
		
		request.setUser(user);
		request.setRequestStatus(RequestStatus.PENDING);
		
		RecycleRequest savedRequest = recycleRequestRepository.save(request);
		return modelMapper.map(savedRequest, RecycleRequestDto.class);
	}

	@Override
	public RecycleRequestDto rejectRequest(int requestId, String reason) {
		RecycleRequest request = recycleRequestRepository.findById(requestId).orElseThrow(() -> new NotFoundException("Request Not Found"));
		request.setRequestStatus(RequestStatus.REJECTED);
		request.setReason(reason);
		RecycleRequest savedRequest = recycleRequestRepository.save(request);
		return modelMapper.map(savedRequest, RecycleRequestDto.class);
	}

	@Override
	public RecycleRequestDto approveRequest(int requestId) {
		RecycleRequest request = recycleRequestRepository.findById(requestId).orElseThrow(() -> new NotFoundException("Request Not Found"));
		request.setRequestStatus(RequestStatus.APPROVED);
		RecycleRequest savedRequest = recycleRequestRepository.save(request);
		return modelMapper.map(savedRequest, RecycleRequestDto.class);
	}

	@Override
	public String setRequestImage(String image, int id) {
		RecycleRequest request = recycleRequestRepository.findById(id).orElseThrow(() -> new NotFoundException("Request Not Found"));
		request.setItemImage(image);
		RecycleRequest savedRequest = recycleRequestRepository.save(request);
		
		return savedRequest.getItemImage();
	}

}
