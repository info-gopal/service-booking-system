package com.javabeans.in.service.booking.system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.javabeans.in.service.booking.system.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	//	@Query("select cl from Reservation cl where cl.company.id= :companyId")
	
	@Query("select r from Review r where r.ad.id= :adId")
	List<Review> findAllReviewByAdId(Long adId);

}
