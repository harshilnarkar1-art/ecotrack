package com.clean.ecotrack.services;

import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayException;

@Service
public interface RazorpayService  {

	Order createOrder(Double amount) throws RazorpayException;
	
	boolean verifyPayment(String orderId, String paymentId, String signature) throws RazorpayException;
	
}
