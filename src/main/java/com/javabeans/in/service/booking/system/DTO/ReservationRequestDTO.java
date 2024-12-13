package com.javabeans.in.service.booking.system.DTO;

import java.util.Date;

import com.javabeans.in.service.booking.system.enumdetail.ReservationStatus;
import com.javabeans.in.service.booking.system.enumdetail.ReviewStatus;

public class ReservationRequestDTO {
	
	private Long id;
	private Date bookDate;
	private Long userId;
	private Long companyId;
	private Long adId;
	private ReservationStatus reservationStatus;
	private ReviewStatus reviewStatus;
	private String userName;
	private String serviceName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}
	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	public ReviewStatus getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(ReviewStatus reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	

}
