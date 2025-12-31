package com.clean.ecotrack.services.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clean.ecotrack.dtos.UserDto;
import com.clean.ecotrack.entites.Role;
import com.clean.ecotrack.entites.User;
import com.clean.ecotrack.enums.AppRole;
import com.clean.ecotrack.exceptions.NotFoundException;
import com.clean.ecotrack.repositories.RoleRepository;
import com.clean.ecotrack.repositories.UserRepository;
import com.clean.ecotrack.services.UserService;





@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRespository;
	@Autowired
	private RoleRepository roleRepository;
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user = modelMapper.map(userDto,User.class);

        // ✅ PASSWORD ENCODE HERE
        user.setPassword(
            passwordEncoder.encode(user.getPassword())
        );
		Role role = roleRepository.findByAppRole(AppRole.ROLE_USER).orElseThrow(()->new NotFoundException("Role not found"));
		user.setRole(role);
		User savedUser = userRespository.save(user);
		UserDto  savedDto = modelMapper.map(savedUser, UserDto.class);
		
		return savedDto;
	}
	

}