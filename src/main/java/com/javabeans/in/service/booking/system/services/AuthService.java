package com.javabeans.in.service.booking.system.services;

import com.javabeans.in.service.booking.system.DTO.SignupRequestDTO;
import com.javabeans.in.service.booking.system.DTO.UserDTO;

public interface AuthService {
	public UserDTO signupClient(SignupRequestDTO signupRequestDTO);
	public boolean isEmailPresent(String email);
	public UserDTO signupCompany(SignupRequestDTO signupRequestDTO);

}
