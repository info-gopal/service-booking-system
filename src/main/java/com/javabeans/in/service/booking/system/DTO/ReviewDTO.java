package com.javabeans.in.service.booking.system.DTO;

import java.util.Date;

public class ReviewDTO {
	
	
	private Long id;
	private String review;
	private Long rating;
	private Date reviewDate;
	private Long userId;
	private Long adId;
	private Long bookId;
	private String serviceName;
	private String clientName;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReview() {
		return review;
	}
	public void setReview(String review) {
		this.review = review;
	}
	public Long getRating() {
		return rating;
	}
	public void setRating(Long rating) {
		this.rating = rating;
	}
	public Date getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getAdId() {
		return adId;
	}
	public void setAdId(Long adId) {
		this.adId = adId;
	}
	public Long getBookId() {
		return bookId;
	}
	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	
	

}
