package com.clean.ecotrack.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkShopDto {
	
	private String name;
	
	private String description;
	
	private Integer price;
	
	private String image;
	
	private LocalDate registrationDate;
	
	private LocalDateTime time;
	
	private int duration; // days
	
	private String venue;

}
