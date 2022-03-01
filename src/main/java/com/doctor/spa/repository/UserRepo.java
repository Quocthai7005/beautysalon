package com.doctor.spa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.spa.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
	 User findByUsername(String username);
}
