package com.javabeans.in.service.booking.system.services;

import java.util.List;

import com.javabeans.in.service.booking.system.DTO.AdDTO;
import com.javabeans.in.service.booking.system.DTO.AdDetailsForClientDTO;
import com.javabeans.in.service.booking.system.DTO.ReservationRequestDTO;
import com.javabeans.in.service.booking.system.DTO.ReviewDTO;

public interface ClientService {
	
	public List<AdDTO> getAllAds();
	public List<AdDTO> searchAdByServiceName(String name);
	public boolean bookService(ReservationRequestDTO requestDTO); 
	public AdDetailsForClientDTO getAdDetailsByAdId(Long adId);
	public List<ReservationRequestDTO> getMyBooking(Long userId);
	public boolean giveReview(ReviewDTO reviewDTO);
	//public boolean giveReveiw(ReviewDTO dto);
}
