package com.majestic.nails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majestic.nails.entity.UserRole;

public interface RoleRepository extends JpaRepository<UserRole, Long> {
	List<UserRole> findByUsername(String username);
}
