package com.cmp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cmp.model.EmployeeDetailsDao;
import com.cmp.model.EmployeeDetailsDto;
import com.cmp.repository.EmployeeDetailsRepoitory;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private EmployeeDetailsRepoitory empDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeeDetailsDao user = empDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public EmployeeDetailsDao save(EmployeeDetailsDto user) {
		EmployeeDetailsDao newUser = new EmployeeDetailsDao();
		newUser.setUsername(user.getUsername());
		newUser.setEmployeeName(user.getEmployeeName());
		newUser.setContactDetails(user.getContactDetails());
		newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
		newUser.setEmailId(user.getEmailId());
		
		return empDao.save(newUser);
	}
}