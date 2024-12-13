package com.javabeans.in.service.booking.system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javabeans.in.service.booking.system.entities.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

	User findFirstByUserEmail(String email);
	
	
}
