package com.clean.ecotrack.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIError {
	
	private String path;
	
	private String error;
	
	private String erroMessage;
	
}
