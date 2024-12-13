package com.javabeans.in.service.booking.system.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javabeans.in.service.booking.system.entities.Reservation;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	
	
	
//	@Query("SELECT r FROM Reservation r WHERE r.company.id = :companyId")
//	List<Reservation> findAllByCompanyId(@Param("companyId") Long companyId);
	
//	@Query("SELECT r FROM Reservation r WHERE r.user.id= :userId")
//	List<Reservation> findAllByUserId(@Param("userId") Long userId);
	
	//SELECT * FROM service_booking_system.reservation where user_id=11;\
	@Query("select r from Reservation r where r.user.id= :userId")
	List<Reservation> findAllReservationByUserId(Long userId);
	//SELECT * FROM service_booking_system.reservation where company_id=1;
	@Query("select cl from Reservation cl where cl.company.id= :companyId")
	List<Reservation> findAllReservationsByCompanyId(Long companyId);
	



}
