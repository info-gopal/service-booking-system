package com.javabeans.in.service.booking.system.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.javabeans.in.service.booking.system.DTO.SignupRequestDTO;
import com.javabeans.in.service.booking.system.DTO.UserDTO;
import com.javabeans.in.service.booking.system.entities.User;
import com.javabeans.in.service.booking.system.enumdetail.UserRoll;
import com.javabeans.in.service.booking.system.repositories.UserRepo;
import com.javabeans.in.service.booking.system.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepo userRepo;

	public UserDTO signupClient(SignupRequestDTO signupRequestDTO) {

		User user = new User();
		//pwd->gopal---->jhgfhgfkhgjdgfdgfshgsgfshghsgfsagfsdgdbgdyte465878tyfghdsjrgw5yruitrby5t4yffgds

		user.setUserEmail(signupRequestDTO.getUserEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
		user.setUserName(signupRequestDTO.getUserName());
		user.setLastName(signupRequestDTO.getLastName());
		user.setMobileNo(signupRequestDTO.getMobileNo());
		user.setRole(UserRoll.CLIENT);

		User savedUser = userRepo.save(user);
		UserDTO userEntityToUserDTO = savedUser.getUserEntityToUserDTO();
		return userEntityToUserDTO;
	}

	public UserDTO signupCompany(SignupRequestDTO signupRequestDTO) {

		User user = new User();

		user.setUserEmail(signupRequestDTO.getUserEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDTO.getPassword()));
		user.setUserName(signupRequestDTO.getUserName());
		user.setLastName(signupRequestDTO.getLastName());
		user.setMobileNo(signupRequestDTO.getMobileNo());
		user.setRole(UserRoll.COMPANY);

		User savedUser = userRepo.save(user);
		UserDTO userEntityToUserDTO = savedUser.getUserEntityToUserDTO();
		return userEntityToUserDTO;
	}

	public boolean isEmailPresent(String email) {

		User userDetail = userRepo.findFirstByUserEmail(email);

		if (userDetail == null) {
			return false;
		}
		// userRepo.findFirstByUserEmail(email)==null;
		return true;
	}

}
