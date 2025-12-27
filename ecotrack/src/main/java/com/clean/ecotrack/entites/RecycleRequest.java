package com.clean.ecotrack.entites;

import com.clean.ecotrack.enums.RequestStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RecycleRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String itemType;
	
	private String itemImage;
	
	private int quantity;
	
	private String reason;
	
	@Enumerated(EnumType.STRING)
	private RequestStatus requestStatus;
	
	@ManyToOne
	@JsonManagedReference 			// ye samajayega
	private User user;

}
