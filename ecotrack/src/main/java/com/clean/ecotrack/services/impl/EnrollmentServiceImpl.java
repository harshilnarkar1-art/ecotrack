package com.clean.ecotrack.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clean.ecotrack.dtos.EnrollmentsDto;
import com.clean.ecotrack.entites.Enrollments;
import com.clean.ecotrack.entites.User;
import com.clean.ecotrack.entites.WorkShop;
import com.clean.ecotrack.enums.PaymentStatus;
import com.clean.ecotrack.exceptions.NotFoundException;
import com.clean.ecotrack.repositories.EnrollmentRepository;
import com.clean.ecotrack.repositories.UserRepository;
import com.clean.ecotrack.repositories.WorkShopRepository;
import com.clean.ecotrack.services.EmailService;
import com.clean.ecotrack.services.EnrollmentService;
import com.clean.ecotrack.services.RazorpayService;
import com.razorpay.Order;
import com.razorpay.RazorpayException;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WorkShopRepository workShopRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private RazorpayService razorpayService;
	
	

	@Override
	public EnrollmentsDto enroll(String userId, int workShopId) {
		if(enrollmentRepository.existsByUserIdAndWorkShopId(userId, workShopId)) {
			throw new RuntimeException("User has already enrolled");
		}
		
		User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User Id Not Found"));
		WorkShop workShop = workShopRepository.findById(workShopId).orElseThrow(() -> new NotFoundException("Workshop Id Not Found"));
		
		Order order;
		try {
			order = razorpayService.createOrder(workShop.getPrice());
		} catch (RazorpayException e) {
			throw new RuntimeException("Unable to create razorpay order");
		}
		
		
		
		Enrollments enrollments = new Enrollments();
		enrollments.setUser(user);
		enrollments.setWorkShop(workShop);
		enrollments.setAmount(workShop.getPrice());
		enrollments.setPaymentStatus(PaymentStatus.CREATED);
		enrollments.setRazorpayOrderId(order.get("id"));
		
		
		
		Enrollments savedEnrollment = enrollmentRepository.save(enrollments);
		
		emailService.sendEmail("priyanka.vibhute@itvedant.com", "Congratulations | Enrollment Completed","Thanks for enrolling for our "+workShop.getName());
		return modelMapper.map(savedEnrollment, EnrollmentsDto.class);
	}



	@Override
	public void confirmPayment(String orderId, String paymentId) {
		Enrollments enrollment = enrollmentRepository.findByRazorpayOrderId(orderId).orElseThrow(() -> new NotFoundException("Enrollment Not Found"));
		enrollment.setRazorpayPaymentId(paymentId);
		enrollment.setPaymentStatus(PaymentStatus.SUCCESS);
		
		enrollmentRepository.save(enrollment);
		
		
	}

}
