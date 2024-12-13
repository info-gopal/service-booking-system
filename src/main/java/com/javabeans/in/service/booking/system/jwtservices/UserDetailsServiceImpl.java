package com.javabeans.in.service.booking.system.jwtservices;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javabeans.in.service.booking.system.entities.User;
import com.javabeans.in.service.booking.system.repositories.UserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String userMail) throws UsernameNotFoundException {
	
		User firstByUserEmail = userRepo.findFirstByUserEmail(userMail);
		if(firstByUserEmail==null) {
			throw new UsernameNotFoundException("username not found",null);
		}
		return new org.springframework.security.core.userdetails.User(firstByUserEmail.getUserEmail(), firstByUserEmail.getPassword(), new ArrayList<>());
	}
}
