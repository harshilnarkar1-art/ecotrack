package com.clean.ecotrack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.clean.ecotrack.entites.User;
import com.clean.ecotrack.repositories.UserRepository;

//import com.sale.furnitureapp.entities.User;
//import com.sale.furnitureapp.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String password) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(password)
		.orElseThrow(() -> new RuntimeException("Email not found"));
		System.out.println(user);
		return user;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public Object is2faEnabled() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}