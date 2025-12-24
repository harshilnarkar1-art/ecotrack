package com.clean.ecotrack.dtos;

import com.clean.ecotrack.entites.User;
import com.clean.ecotrack.enums.RequestStatus;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecycleRequestDto {
	
	private int id;
	
	private String itemType;
	
	private String itemImage;
	
	private int quantity;
	
	private RequestStatus requestStatus;
	
	private User user;


}
