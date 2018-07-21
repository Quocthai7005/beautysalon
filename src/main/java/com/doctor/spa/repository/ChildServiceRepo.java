package com.doctor.spa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.entity.ChildService;

public interface ChildServiceRepo extends JpaRepository<ChildService, Long>{

	// Admin's query
	
	@Query(value = "SELECT * FROM child_service cs WHERE cs.parent_service_id = ?1 AND cs.is_deleted = 0 LIMIT 4", nativeQuery = true)
	List<ChildService> findFirst4ByServiceGroupIdByDeletedFalse(Long id);
	
	@Query(value = "SELECT * FROM child_service s WHERE s.url LIKE ?1 AND s.id != ?2 AND s.is_deleted = 0", nativeQuery = true)
	List<ChildService> findByUrlByIdNotEqualByDeletedFalse(String url, Long id);
	
	@Query(value = "SELECT cs FROM ChildService cs WHERE cs.parentService.id = ?1 AND cs.deleted = 0")
	Page<ChildService> findByServiceGroupIdByDeletedFalse(Long id, Pageable pageable);
	
	@Modifying
	@Query(value = "UPDATE child_service cs SET cs.is_deleted=1 WHERE cs.id IN ?1", nativeQuery = true)
	void deleteByIds(List<Long> ids);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE child_service s SET s.is_deleted=1 WHERE s.id = ?1", nativeQuery = true)
	void deleteById(Long id);
	
	// Others' query
	
	List<ChildService> getByIsShownHomeTrue();
	
	@Query(value = "SELECT * FROM child_service s WHERE s.url LIKE ?1 AND s.is_deleted = 0", nativeQuery = true)
	List<ChildService> findByUrlByDeletedFalse(String url);
	
	//List<ChildService> findByParentServiceId(Long id);
	
	ChildService findById(Long id);
	
	ChildService findByUrl(String url);
	
	List<ChildService> findByUrlNotLikeAndDeletedFalse(String url);

	Page<ChildService> findByDeletedFalse(Pageable pageable);
	
	List<ChildService> findByDeletedFalse();
}
