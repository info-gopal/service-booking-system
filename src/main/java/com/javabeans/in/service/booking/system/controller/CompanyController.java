package com.javabeans.in.service.booking.system.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javabeans.in.service.booking.system.DTO.AdDTO;
import com.javabeans.in.service.booking.system.DTO.ReservationRequestDTO;
import com.javabeans.in.service.booking.system.services.CompanyService;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	// http://localhost:8080/api/company/ad/1
	@PostMapping("/ad/{userId}")
	public ResponseEntity<?> postAd(@PathVariable Long userId, @ModelAttribute AdDTO adDTO) throws IOException {

		boolean postAd = companyService.postAd(userId, adDTO);

		if (postAd) {
			return new ResponseEntity<>("ad succufully saved", HttpStatus.OK);

		} else {
			return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
		}

	}

	// http://localhost:8080/api/company/ad/1
	@GetMapping("/ads/{userId}")
	public ResponseEntity<?> getAllAdsByUser(@PathVariable Long userId) {

		List<AdDTO> response = companyService.getAllAdsByUser(userId);

		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
	}

	// http://localhost:8080/api/company/ad/1
	@GetMapping("/ad/{adId}")
	public ResponseEntity<?> getAdByAdId(@PathVariable Long adId) {

		AdDTO adDTO = companyService.getAdByAdId(adId);
		if (adDTO != null) {
			return ResponseEntity.ok(adDTO);
		}

		return new ResponseEntity<>("Ad id is not found", HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/ad/{adId}")
public ResponseEntity<?> updateAd(@PathVariable Long adId,@ModelAttribute AdDTO adDTO) throws IOException{
	
	boolean updatedAd = companyService.updatedAd(adId, adDTO);
	
	if(updatedAd) {
		return ResponseEntity.ok().build();		
	}
	
	return new ResponseEntity<>("Ad NOT Found",HttpStatus.NOT_FOUND);
	
}
	@DeleteMapping("/ad/{adId}")
	public ResponseEntity<?> deleteAdById(@PathVariable Long adId){
				
		boolean deleteAd = companyService.deleteAd(adId);
				
		if(deleteAd) {
			return ResponseEntity.ok().build();
		}
		return new ResponseEntity<>("Ad Id NOT Found",HttpStatus.NOT_FOUND);
		
	}

//	@GetMapping("/bookings/{companyId}")
//	public ResponseEntity<List<ReservationRequestDTO>> getAllBookings(@PathVariable Long companyId){
//		return ResponseEntity.ok(companyService.getAllAdBookings(companyId));
//	}
//	@GetMapping("/bookings/{bookingId}/{status}")
//	public ResponseEntity<?> changeBookingStatus(@PathVariable Long bookingId,@PathVariable String status){
//		boolean isSaved=companyService.changeBookingStatus(bookingId, status);
//		if(isSaved) {
//			return ResponseEntity.ok().build();
//		}
//		return ResponseEntity.notFound().build();
//	}
//	
	@GetMapping("/bookings/{companyId}")
	public ResponseEntity<List<ReservationRequestDTO>> getMyAllBooking(@PathVariable Long companyId){
		return ResponseEntity.ok(companyService.getAllMybookings(companyId));
	}
	
	@GetMapping("bookings/{bookingId}/{status}")
	public ResponseEntity<?> bookingUdate(@PathVariable Long bookingId, @PathVariable String status) {
		boolean isSaved = companyService.BookingUpdate(bookingId, status);

		if (isSaved) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}

	}
	
	
	
}
