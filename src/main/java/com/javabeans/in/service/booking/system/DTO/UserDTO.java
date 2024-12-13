package com.javabeans.in.service.booking.system.DTO;

import com.javabeans.in.service.booking.system.enumdetail.UserRoll;

public class UserDTO {
	
	private String userName;
	private String userEmail;
	private String password;
	private String lastName;
	private String mobileNo;
	private UserRoll role;
	
	
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
