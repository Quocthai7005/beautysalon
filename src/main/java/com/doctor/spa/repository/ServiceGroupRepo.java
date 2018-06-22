package com.doctor.spa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.entity.ServiceGroup;

public interface ServiceGroupRepo extends JpaRepository<ServiceGroup, Long> {
	
	// Admin's query
	List<ServiceGroup> findByDeletedFalse();
	
	Page<ServiceGroup> findByDeletedFalse(Pageable pageable);
	
	Integer countByDeletedFalse();
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE service s SET s.is_deleted=1 WHERE s.id = ?1", nativeQuery = true)
	void deleteByIdDeletedFalse(Long id);
	
	// Others' query
	
	ServiceGroup findById(Long id);

	ServiceGroup findByUrl(String url);
	
	List<ServiceGroup> findByDeletedFalseAndUrlNotLike(String url);
	
	@Query(value = "SELECT * FROM service s WHERE s.url LIKE ?1 AND s.id != ?2 AND s.is_deleted = 0", nativeQuery = true)
	List<ServiceGroup> findByUrlByIdNotEqual(String url, Long id);
	
	@Query(value = "SELECT * FROM service s WHERE s.url LIKE ?1 AND s.is_deleted = 0", nativeQuery = true)
	List<ServiceGroup> findServicesByUrl(String url);
}
