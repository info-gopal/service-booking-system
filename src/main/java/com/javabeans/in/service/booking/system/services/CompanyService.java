package com.javabeans.in.service.booking.system.services;

import java.io.IOException;
import java.util.List;

import com.javabeans.in.service.booking.system.DTO.AdDTO;
import com.javabeans.in.service.booking.system.DTO.ReservationRequestDTO;
import com.javabeans.in.service.booking.system.entities.Ad;

public interface CompanyService {
	 boolean postAd(Long userId,AdDTO adDTO) throws IOException;
	 public List<AdDTO>  getAllAdsByUser(Long userId);
	 public AdDTO getAdByAdId(Long adId);
	 public boolean updatedAd(Long adId,AdDTO adDTO) throws IOException;
	 public boolean deleteAd(Long adId);
	 public List<ReservationRequestDTO> getAllMybookings(Long companyId);	
	 public boolean changeBookingStatus(Long bookingId,String status);
//	 public List<AdDTO> getAllAds();
//	 public List<AdDTO> searchAdByName(String name);
	 public boolean BookingUpdate(Long bookingId, String status);

}
