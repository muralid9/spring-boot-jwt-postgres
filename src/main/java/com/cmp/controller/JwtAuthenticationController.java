package com.cmp.controller;

import com.cmp.config.JwtTokenUtil;
import com.cmp.model.EmployeeDetailsDao;
import com.cmp.model.EmployeeDetailsDto;
import com.cmp.model.JwtRequest;
import com.cmp.model.JwtResponse;
import com.cmp.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService emloyeeDetailsService;

	@PostMapping(value = "/authenticate")
	public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		UserDetails userDetails = emloyeeDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	@PostMapping(value = "/register")
	public ResponseEntity<EmployeeDetailsDao> saveUser(@RequestBody EmployeeDetailsDto user) throws Exception {
		return ResponseEntity.ok(emloyeeDetailsService.save(user));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
