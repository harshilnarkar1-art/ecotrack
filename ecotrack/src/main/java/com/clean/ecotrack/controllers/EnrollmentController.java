package com.clean.ecotrack.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clean.ecotrack.dtos.EnrollmentsDto;
import com.clean.ecotrack.services.EnrollmentService;
import com.clean.ecotrack.services.RazorpayService;
import com.clean.ecotrack.services.UserService;
import com.clean.ecotrack.services.WorkShopService;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/enroll")
@CrossOrigin("http://localhost:3000/")
public class EnrollmentController {
	
	@Autowired
	private WorkShopService workShopService;
	
	@Autowired
	private RazorpayService razorpayService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EnrollmentService enrollmentService;
	
	
	@PostMapping("/{userId}/{workshopId}")
	public ResponseEntity<EnrollmentsDto> addEnroll(@PathVariable String userId,@PathVariable int workshopId){
		
		return ResponseEntity.ok(enrollmentService.enroll(userId, workshopId));
	}
	
	@PostMapping("/confirm")
	public ResponseEntity<String> confirmEnrollment(@RequestBody Map<String, String> payload)  throws RazorpayException {
		boolean verified = razorpayService.verifyPayment(payload.get("razorpayOrderId"), payload.get("razorpayPaymentId"), payload.get("razorpaySignature"));
		
		if(!verified) {
			return ResponseEntity.badRequest().body("Payment Varification Failed");
		}
		
		enrollmentService.confirmPayment(payload.get("razorpayOrderId"), payload.get("razorpayPaymentId"));
		return ResponseEntity.ok("Enrollment succesfull");
	}

}
