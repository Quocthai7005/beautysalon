package com.majestic.nails.repository;

import com.majestic.nails.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUsername(String username);
}
