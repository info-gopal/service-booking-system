package com.javabeans.in.service.booking.system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javabeans.in.service.booking.system.entities.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

	// select a from ads a where a.user;
	@Query("select a from Ad a where a.user.id= :userId ")
	List<Ad> findByUserId(Long userId);
	
	List<Ad> findAllByServiceNameContaining(String name);

	

}
