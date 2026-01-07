package com.clean.ecotrack.dtos;

import java.time.LocalDateTime;

import com.clean.ecotrack.entites.User;
import com.clean.ecotrack.entites.WorkShop;
import com.clean.ecotrack.enums.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentsDto {
	
	private User user;
	
	private WorkShop workShop;
	
	private LocalDateTime enrolledAt;
	
	private Double amount;
	private String razorpayOrderId;
	private String razorpayPaymentId;
	
	private PaymentStatus paymentStatus;

}
