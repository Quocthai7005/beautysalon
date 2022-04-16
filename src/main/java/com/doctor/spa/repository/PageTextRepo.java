package com.doctor.spa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doctor.spa.entity.PageText;

public interface PageTextRepo extends JpaRepository<PageText, Long>{
	
	PageText findByPageInAndSectionIn(String page, String section);
	
	List<PageText> findByPage(String page);
	
	List<PageText> findBySection(String section);
	
	List<PageText> findBySectionAndIsShownHomeTrue(String section);
	
	PageText findById(Long id);
	
	PageText findByTitle(String title);
}
