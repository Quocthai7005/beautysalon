package com.doctor.spa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.doctor.spa.entity.PageText;

public interface PageTextRepo extends JpaRepository<PageText, Long>{
	
	PageText findByPageInAndSectionIn(String page, String section);
	
	List<PageText> findByPage(String page);
	
	List<PageText> findBySection(String section);
	
	@Query(value = "SELECT * FROM micellaneous_text s WHERE s.section LIKE ?1 AND s.is_deleted = 0 AND s.isShownHome = 1", nativeQuery = true)
	List<PageText> findBySectionAndIsShownHomeTrue(String section);
	
	PageText findById(Long id);
}
