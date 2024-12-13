package com.javabeans.in.service.booking.system.controller;

import java.io.IOException;
import java.net.http.HttpClient;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javabeans.in.service.booking.system.DTO.AuthenticationRequest;
import com.javabeans.in.service.booking.system.DTO.SignupRequestDTO;
import com.javabeans.in.service.booking.system.DTO.UserDTO;
import com.javabeans.in.service.booking.system.entities.User;
import com.javabeans.in.service.booking.system.jwtservices.UserDetailsServiceImpl;
import com.javabeans.in.service.booking.system.repositories.UserRepo;
import com.javabeans.in.service.booking.system.security.JwtUtil;
import com.javabeans.in.service.booking.system.services.AuthService;

import jakarta.servlet.http.HttpServletResponse;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {
	public static final String TOKEN_PREFEX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	@Autowired
	private AuthService authService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	UserDetailsServiceImpl userServiceImpl;
	@Autowired
	UserRepo userRepo;
	@Autowired
	JwtUtil jwtUtil;

	// http://localhost:8080/client/signup
	@PostMapping("/client/signup")
	public ResponseEntity<?> signUpClient(@RequestBody SignupRequestDTO signupRequestDTO) {
		if (signupRequestDTO.getUserEmail() == null || signupRequestDTO.getPassword() == null
				|| signupRequestDTO.getUserName() == null || signupRequestDTO.getUserName() == "") {
			return new ResponseEntity<>(" email , password and username should not be empty", HttpStatus.BAD_REQUEST);
		}

		if (authService.isEmailPresent(signupRequestDTO.getUserEmail())) {
			return new ResponseEntity<>("your email is already exists", HttpStatus.NOT_ACCEPTABLE);
		}

		UserDTO responseUserDTO = authService.signupClient(signupRequestDTO);

		return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
	}

	@PostMapping("/company/signup")
	public ResponseEntity<?> signUpCompany(@RequestBody SignupRequestDTO signupRequestDTO) {
		if (signupRequestDTO.getUserEmail() == null) {
			return new ResponseEntity<>(" email should not empty", HttpStatus.BAD_REQUEST);
		}

		if (authService.isEmailPresent(signupRequestDTO.getUserEmail())) {
			return new ResponseEntity<>("your email is already exists", HttpStatus.NOT_ACCEPTABLE);
		}

		UserDTO responseUserDTO = authService.signupCompany(signupRequestDTO);

		return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
	}

	@PostMapping("/{authenticate}")
	public void createAuthenticationTaken(@RequestBody AuthenticationRequest authenticationRequest,
			HttpServletResponse response) throws IOException, JSONException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new BadCredentialsException("username or password incorrect ", e);
		}
		UserDetails userByUsername = userServiceImpl.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwtToken = jwtUtil.generateToken(userByUsername.getUsername());
		
		User firstByUserEmail = userRepo.findFirstByUserEmail(authenticationRequest.getUsername());
		response.getWriter().write(new JSONObject().put("userId", firstByUserEmail.getUserId())
				.put("role", firstByUserEmail.getRole()).toString()

		);
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Access-Control-Allow-Headers",
				"Authorization," + " X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
		response.addHeader(HEADER_STRING, TOKEN_PREFEX + jwtToken);

	}

}
