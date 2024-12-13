package com.javabeans.in.service.booking.system.DTO;

import java.util.List;

public class AdDetailsForClientDTO {
	
	private AdDTO adDto;
	private List<ReviewDTO> reviews ;
	

	public List<ReviewDTO> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewDTO> reviews) {
		this.reviews = reviews;
	}

	public AdDTO getAdDto() {
		return adDto;
	}

	public void setAdDto(AdDTO adDto) {
		this.adDto = adDto;
	}
	

}
