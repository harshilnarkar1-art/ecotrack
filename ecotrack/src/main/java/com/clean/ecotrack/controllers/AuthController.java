package com.clean.ecotrack.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.clean.ecotrack.entites.User;
import com.clean.ecotrack.repositories.UserRepository;
import com.clean.ecotrack.security.LoginRequest;
import com.clean.ecotrack.security.LoginResponse;
import com.clean.ecotrack.security.jwt.JwtUtils;
import com.clean.ecotrack.dtos.UserDto;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest loginRequest) {

        Authentication authentication;

        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // ✅ CORRECT: principal IS User
        User user = (User) authentication.getPrincipal();

        // ✅ Generate token using username (email)
        String token = jwtUtils.generateJwtToken(user.getUsername());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUserDto(
                modelMapper.map(user, UserDto.class)
        );

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }
}
