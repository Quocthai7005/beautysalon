package com.doctor.spa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.spa.entity.AUser;

public interface UserRepo extends JpaRepository<AUser, Long> {
	 AUser findByUsername(String username);
}
