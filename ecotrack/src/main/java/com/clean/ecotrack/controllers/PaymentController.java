package com.clean.ecotrack.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.RazorpayClient;

import jakarta.validation.Valid;

@RestController
public class PaymentController {
	
	@Value("${razorpay.key}")
	private String RazorpayKey;
	
	@Value("${razorpay.secret}")
	private String razorpaySecret;
	
	
	@PostMapping("/payment")
	public ResponseEntity<Map<String, String>>payment(){
		try {
			new RazorpayClient(RazorpayKey, razorpaySecret);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	

}
