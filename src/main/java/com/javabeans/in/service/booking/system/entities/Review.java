package com.javabeans.in.service.booking.system.entities;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.javabeans.in.service.booking.system.DTO.ReviewDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String review;
	private Long rating;
	private Date reviewDate;
	
	
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="user_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY,optional = false)
	@JoinColumn(name="ad_id",nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Ad ad;
	
	public ReviewDTO getReviewDto() {
		ReviewDTO dto=new ReviewDTO();
		dto.setAdId(id);
		//dto.setBookId(ad.get);
		dto.setClientName(user.getUserName());
		dto.setRating(rating);
		dto.setReview(review);
		dto.setReviewDate(reviewDate);
		
		return dto;
	}
	
	
	
	
	

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Ad getAd() {
		return ad;
	}

	public void setAd(Ad ad) {
		this.ad = ad;
	}
	
	
	

}
