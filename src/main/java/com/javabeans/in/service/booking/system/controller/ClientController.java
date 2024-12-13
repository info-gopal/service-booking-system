package com.javabeans.in.service.booking.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javabeans.in.service.booking.system.DTO.AdDTO;
import com.javabeans.in.service.booking.system.DTO.AdDetailsForClientDTO;
import com.javabeans.in.service.booking.system.DTO.ReservationRequestDTO;
import com.javabeans.in.service.booking.system.DTO.ReviewDTO;
import com.javabeans.in.service.booking.system.services.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	@Autowired
	ClientService clientService;
	
	//http://localhost:8080/api/client/ads
	@GetMapping("/ads")
	public ResponseEntity<?> getAllAds(){		
		return ResponseEntity.ok(clientService.getAllAds());
	}
	//http://localhost:8080/api/client/search/servicename
	@GetMapping("/search/{name}")
	public ResponseEntity<?> serchAdByService(@PathVariable String name){
		List<AdDTO> searchAdByServiceName = clientService.searchAdByServiceName(name);
		return ResponseEntity.ok(searchAdByServiceName);
		
	}
	
	//http://localhost:8080/api/client/bookingService
	@PostMapping("/ad/bookingService")
	public ResponseEntity<?> bookingSevice(@RequestBody ReservationRequestDTO requestDTO){
		boolean isBooked = clientService.bookService(requestDTO);
		if(isBooked) {
			
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}	
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	//http://localhost:8080/api/client/ad/10
	@GetMapping("/ad/{adId}")
	public ResponseEntity<?> getAdByADId(@PathVariable Long adId){
		AdDetailsForClientDTO adDetailsByAdId = clientService.getAdDetailsByAdId(adId);
		return ResponseEntity.ok(adDetailsByAdId);
	}
	
	@GetMapping("/allMyBooking/{userId}")
	public ResponseEntity<?> getAllMyBookings(@PathVariable Long userId){
		List<ReservationRequestDTO> myBookings= clientService.getMyBooking(userId);	
		
		return ResponseEntity.ok(myBookings);
	}
	@PostMapping("/review")
	public ResponseEntity<?> giveReview(@RequestBody ReviewDTO reviewDTO){
		
		boolean isReviewed = clientService.giveReview(reviewDTO);
		if(isReviewed) {
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
				
	}
	
	
	
	
}
