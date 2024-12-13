package com.javabeans.in.service.booking.system.servicesImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javabeans.in.service.booking.system.DTO.AdDTO;
import com.javabeans.in.service.booking.system.DTO.ReservationRequestDTO;
import com.javabeans.in.service.booking.system.entities.Ad;
import com.javabeans.in.service.booking.system.entities.Reservation;
import com.javabeans.in.service.booking.system.entities.User;
import com.javabeans.in.service.booking.system.enumdetail.ReservationStatus;
import com.javabeans.in.service.booking.system.repositories.AdRepository;
import com.javabeans.in.service.booking.system.repositories.ReservationRepository;
import com.javabeans.in.service.booking.system.repositories.UserRepo;
import com.javabeans.in.service.booking.system.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	AdRepository adRepository;
	@Autowired
	UserRepo userRepo;
	@Autowired
    ReservationRepository reservationRepository;
	

	@Override
	public boolean postAd(Long userId, AdDTO adDTO) throws IOException {

		Optional<User> optinalUser = userRepo.findById(userId);

		if (optinalUser.isPresent()) {
			Ad ad = new Ad();
			ad.setDescription(adDTO.getDescription());
			ad.setImg(adDTO.getFile().getBytes());
			ad.setPrice(adDTO.getPrice());
			ad.setServiceName(adDTO.getServiceName());
			ad.setUser(optinalUser.get());
			adRepository.save(ad);
			return true;
		}

		return false;
	}

	public List<AdDTO> getAllAdsByUser(Long userId) {
		List<Ad> byUserId = adRepository.findByUserId(userId);

		List<AdDTO> adDTOs = new ArrayList<>();

		for (Ad ad : byUserId) {

			AdDTO adDTO = new AdDTO();
			adDTO.setAdId(ad.getAdId());
			adDTO.setDescription(ad.getDescription());
			adDTO.setServiceName(ad.getServiceName());
			adDTO.setImg(ad.getImg());
			adDTO.setPrice(ad.getPrice());
			adDTO.setCompanyName(ad.getUser().getUserName());
			// listDtos
			adDTOs.add(adDTO);

		}

		return adDTOs;

	}

	public AdDTO getAdByAdId(Long adId) {

		Optional<Ad> optionalAd = adRepository.findById(adId);
		if (optionalAd.isPresent()) {
			Ad ad = optionalAd.get();
			return ad.getDto();
		}
		return null;
	}

	public boolean updatedAd(Long adId,AdDTO adDTO) throws IOException {
		
		Optional<Ad> adData = adRepository.findById(adId);
		
		if(adData.isPresent()) {
			Ad ad = adData.get();
			ad.setDescription(adDTO.getDescription());
			ad.setServiceName(adDTO.getServiceName());
			ad.setPrice(adDTO.getPrice());
			if(adDTO.getFile()!=null) {
				ad.setImg(adDTO.getFile().getBytes());
			}
			adRepository.save(ad);			
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteAd(Long adId) {
		Optional<Ad> adData = adRepository.findById(adId);
		if(adData.isPresent()) {
			adRepository.deleteById(adId);
			return true;
		}
		return false;
	}
	public List<AdDTO> getAllAds() {
		List<Ad> byUserId = adRepository.findAll();
		List<AdDTO> adDTOs = new ArrayList<>();
		for (Ad ad : byUserId) {
			AdDTO adDTO = new AdDTO();
			adDTO.setAdId(ad.getAdId());
			adDTO.setDescription(ad.getDescription());
			adDTO.setServiceName(ad.getServiceName());
			adDTO.setImg(ad.getImg());
			adDTO.setPrice(ad.getPrice());
			adDTO.setCompanyName(ad.getUser().getUserName());
			// listDtos
			adDTOs.add(adDTO);

		}
		return adDTOs;
	}
	
	
	
	public boolean changeBookingStatus(Long bookingId,String status) {
		Optional<Reservation> byId = reservationRepository.findById(bookingId);
		if(byId.isPresent()) {
			Reservation exsitReservation=byId.get();
			if(Objects.equals(status, "Approve")) {
				exsitReservation.setReservationStatus(ReservationStatus.APPROVED);
			}else {
				exsitReservation.setReservationStatus(ReservationStatus.REJECTED);	
			}
			reservationRepository.save(exsitReservation);
			return true;
		}
		return false;
	}
	
	
	
	
	public List<ReservationRequestDTO> getAllMybookings(Long companyId){	
		
		List<Reservation> allReservationsByCompanyId = reservationRepository.findAllReservationsByCompanyId(companyId);
		List<ReservationRequestDTO> reservationdtoList=new ArrayList<>();
		for(Reservation res:allReservationsByCompanyId) {
			
			ReservationRequestDTO reservationdto = res.getDto();
			reservationdtoList.add(reservationdto);		
		}
		
		return reservationdtoList;
		
	}
	
	
	public boolean BookingUpdate(Long bookingId, String status) {

		Optional<Reservation> byId = reservationRepository.findById(bookingId);
		if (byId.isPresent()) {
			Reservation oldReservationDetails = byId.get();
			if (status.equals("Approve")) {
				oldReservationDetails.setReservationStatus(ReservationStatus.APPROVED);
			} else {
				oldReservationDetails.setReservationStatus(ReservationStatus.REJECTED);
			}
			reservationRepository.save(oldReservationDetails);
			return true;
		}

		return false;
	}
	
	
	
}
