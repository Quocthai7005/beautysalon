package com.doctor.spa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.spa.entity.UserRole;

public interface RoleRepo extends JpaRepository<UserRole, Long> {
	List<UserRole> findByUsername(String username);
}
