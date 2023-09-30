package com.majestic.nails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.majestic.nails.entity.PageText;

public interface PageTextRepository extends JpaRepository<PageText, Long>{
	
	PageText findByPageInAndSectionIn(String page, String section);
	
	List<PageText> findByPage(String page);
	
	List<PageText> findBySection(String section);
	
	List<PageText> findBySectionAndIsShownHomeTrue(String section);
	
	PageText findById(Long id);
	
	PageText findByTitle(String title);
}
