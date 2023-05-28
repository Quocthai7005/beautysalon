package com.doctor.spa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.spa.entity.PageText;

public interface MessengerRepository extends JpaRepository<PageText, Long>{
	
	List<PageText> findBySection(String section);
	
	PageText findByTitle(String title);
	
}
