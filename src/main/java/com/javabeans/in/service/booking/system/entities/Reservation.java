package com.javabeans.in.service.booking.system.entities;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.javabeans.in.service.booking.system.DTO.ReservationRequestDTO;
import com.javabeans.in.service.booking.system.enumdetail.ReservationStatus;
import com.javabeans.in.service.booking.system.enumdetail.ReviewStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date bookDate;
	//booking user
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "userId",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	//service provider
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "companyId",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User company;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name = "adId",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ad ad;
	
	
	private ReservationStatus reservationStatus;
	private ReviewStatus reviewStatus;
	
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getCompany() {
		return company;
	}
	public void setCompany(User company) {
		this.company = company;
	}
	public Ad getAd() {
		return ad;
	}
	public void setAd(Ad ad) {
		this.ad = ad;
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
	
	
	
	public ReservationRequestDTO getDto() {		
		ReservationRequestDTO requestDTO=new ReservationRequestDTO();		
		requestDTO.setBookDate(bookDate);		
		requestDTO.setReservationStatus(reservationStatus);
		requestDTO.setReviewStatus(reviewStatus);
		requestDTO.setServiceName(ad.getServiceName());
		requestDTO.setAdId(ad.getAdId());
		requestDTO.setUserId(user.getUserId());
		requestDTO.setCompanyId(company.getUserId());
		requestDTO.setUserName(user.getUserName());
		requestDTO.setId(id);			
		return requestDTO;
	}
	
}
