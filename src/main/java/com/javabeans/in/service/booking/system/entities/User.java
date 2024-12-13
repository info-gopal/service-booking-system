package com.javabeans.in.service.booking.system.entities;

import com.javabeans.in.service.booking.system.DTO.UserDTO;
import com.javabeans.in.service.booking.system.enumdetail.UserRoll;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_detail")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String userName;
	private String userEmail;
	private String password;
	private String lastName;
	private String mobileNo;
	private UserRoll role;
	
	
	
	public UserDTO getUserEntityToUserDTO() {
		
		UserDTO userDTO=new UserDTO();
		userDTO.setUserName(userName);
		userDTO.setLastName(lastName);
		userDTO.setMobileNo(mobileNo);
		userDTO.setPassword(password);
		userDTO.setRole(role);
		userDTO.setUserEmail(userEmail);
		return userDTO;
	}
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public UserRoll getRole() {
		return role;
	}
	public void setRole(UserRoll role) {
		this.role = role;
	}
	

}
