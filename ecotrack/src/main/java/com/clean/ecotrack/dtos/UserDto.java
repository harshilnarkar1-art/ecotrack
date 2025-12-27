package com.clean.ecotrack.dtos;

import java.util.List;

import com.clean.ecotrack.entites.RecycleRequest;
import com.clean.ecotrack.entites.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String id;
	
	
	private String name;
	
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	private int age;
	
	private List<RecycleRequest> recycleRequests;
	
	
	private Role role;


}
