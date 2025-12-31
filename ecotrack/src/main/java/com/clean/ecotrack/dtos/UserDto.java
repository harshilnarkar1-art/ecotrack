package com.clean.ecotrack.dtos;

import java.util.List;

import com.clean.ecotrack.entites.RecycleRequest;
import com.clean.ecotrack.entites.Role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String id;
	
	@NotNull(message = "Name cannot be null")
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 2, max = 40,message = "Size cannot be valid size")
	private String name;
	
	@Pattern(regexp = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$")
	private String email;
	
	@NotNull(message = "Password cannot be null")
	@NotBlank(message = "Password cannot be blank")
	@Pattern(regexp = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,})\\S$")
	private String password;
	
	private String phoneNumber;
	
	private int age;
	
	private List<RecycleRequest> recycleRequests;
	
	
	private Role role;


}
