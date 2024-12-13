package com.javabeans.in.service.booking.system.servicesImp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javabeans.in.service.booking.system.DTO.AdDTO;
import com.javabeans.in.service.booking.system.DTO.AdDetailsForClientDTO;
import com.javabeans.in.service.booking.system.DTO.ReservationRequestDTO;
import com.javabeans.in.service.booking.system.DTO.ReviewDTO;
import com.javabeans.in.service.booking.system.entities.Ad;
import com.javabeans.in.service.booking.system.entities.Reservation;
import com.javabeans.in.service.booking.system.entities.Review;
import com.javabeans.in.service.booking.system.entities.User;
import com.javabeans.in.service.booking.system.enumdetail.ReservationStatus;
import com.javabeans.in.service.booking.system.enumdetail.ReviewStatus;
import com.javabeans.in.service.booking.system.repositories.AdRepository;
import com.javabeans.in.service.booking.system.repositories.ReservationRepository;
import com.javabeans.in.service.booking.system.repositories.ReviewRepository;
import com.javabeans.in.service.booking.system.repositories.UserRepo;
import com.javabeans.in.service.booking.system.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	AdRepository adRepository;
	@Autowired
	UserRepo userRepo;
	@Autowired
	ReservationRepository reservationRepo;
	@Autowired
	ReviewRepository reviewRepo;
	
	

	public List<AdDTO> getAllAds() {
		List<Ad> adsList = adRepository.findAll();

		List<AdDTO> adDTOs = new ArrayList<>();

		for (Ad ad : adsList) {

			AdDTO adDTO = new AdDTO();
			adDTO.setAdId(ad.getAdId());
			adDTO.setCompanyName(ad.getUser().getUserName());
			adDTO.setDescription(ad.getDescription());
			adDTO.setImg(ad.getImg());
			adDTO.setPrice(ad.getPrice());
			adDTO.setServiceName(ad.getServiceName());
			adDTOs.add(adDTO);
		}

		return adDTOs;

	}

	public List<AdDTO> searchAdByServiceName(String name) {

		List<Ad> ads = adRepository.findAllByServiceNameContaining(name);

		List<AdDTO> adDTOs = new ArrayList<>();

		for (Ad ad : ads) {

			AdDTO adDTO = new AdDTO();
			adDTO.setAdId(ad.getAdId());
			adDTO.setCompanyName(ad.getUser().getUserName());
			adDTO.setDescription(ad.getDescription());
			adDTO.setImg(ad.getImg());
			adDTO.setPrice(ad.getPrice());
			adDTO.setServiceName(ad.getServiceName());
			adDTOs.add(adDTO);
		}

		return adDTOs;

	}	
	
	public boolean bookService(ReservationRequestDTO requestDTO) {
		
		Optional<Ad> isADAvailable = adRepository.findById(requestDTO.getAdId());
		Optional<User> isUserAvailable = userRepo.findById(requestDTO.getUserId());
		
		if(isADAvailable.isPresent() && isUserAvailable.isPresent()) {
			
			Reservation reservation=new Reservation();
			Ad ad = isADAvailable.get();
			User user = isUserAvailable.get();
			
			reservation.setAd(ad);
			reservation.setBookDate(requestDTO.getBookDate());
			reservation.setCompany(ad.getUser());
			reservation.setReservationStatus(ReservationStatus.PENDING);
			reservation.setReviewStatus(ReviewStatus.FALSE);			
			reservation.setUser(user);		
			reservationRepo.save(reservation);
			
			return true;
		}			
		return false;
		
	}
//	public AdDetailsForClientDTO getAdDetailsforByAdId(Long adId) {
//		Optional<Ad> oplAd = adRepository.findById(adId);
//		AdDetailsForClientDTO adDetailsForClientDTO=new AdDetailsForClientDTO();
//		if(oplAd.isPresent()) {
//			adDetailsForClientDTO.setAdDTO(oplAd.get().getDto());
//		}		
//		return adDetailsForClientDTO;
//		
//	}

	
	
	public AdDetailsForClientDTO getAdDetailsByAdId(Long adId) {
		
		Optional<Ad> optionalAd=adRepository.findById(adId);	
		
		
		AdDetailsForClientDTO responseAdDto=new AdDetailsForClientDTO();
		if(optionalAd.isPresent()) {
			   AdDTO dto = optionalAd.get().getDto();
			   responseAdDto.setAdDto(dto);			   
			   
			   List<Review> allReviewByAdId = reviewRepo.findAllReviewByAdId(adId);
			   List<ReviewDTO>reviewDTOs=new ArrayList<>();
			   for(Review review:allReviewByAdId) {
				   ReviewDTO reviewDto = review.getReviewDto();
				   reviewDTOs.add(reviewDto);
			   }
			   responseAdDto.setReviews(reviewDTOs);
			   
		}			
		return responseAdDto;
	}
	

	public List<ReservationRequestDTO> getMyBooking(Long userId) {

		List<Reservation> reservationByUserId = reservationRepo.findAllReservationByUserId(userId);

		List<ReservationRequestDTO> reservationRequestDTOList = new ArrayList<>();

		for (Reservation res : reservationByUserId) {

			ReservationRequestDTO dto = res.getDto();

			reservationRequestDTOList.add(dto);
		}

		return reservationRequestDTOList;

	}
	
public boolean giveReview(ReviewDTO reviewDTO) {
	
	Optional<User> userDetails = userRepo.findById(reviewDTO.getUserId());
	Optional<Reservation> bookDetails = reservationRepo.findById(reviewDTO.getBookId());
	if(userDetails.isPresent() && bookDetails.isPresent()) {
		
		Review review=new Review();
		Ad ad = bookDetails.get().getAd();
		review.setAd(ad);
		review.setRating(reviewDTO.getRating());
		review.setReview(reviewDTO.getReview());
		//System.err.println(new Date());
		review.setReviewDate(new Date());
		review.setUser(userDetails.get());		
		reviewRepo.save(review);
		Reservation reservation = bookDetails.get();
		reservation.setReviewStatus(ReviewStatus.TRUE);
		reservationRepo.save(reservation);		
		return true;
	}
	
		
	return false;
}
}
