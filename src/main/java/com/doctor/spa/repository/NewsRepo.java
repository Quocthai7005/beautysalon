package com.doctor.spa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.entity.News;

@Repository
public interface NewsRepo extends PagingAndSortingRepository<News, Long> {
	
	// Admin's query

	Page<News> findByProductIdAndNameContainsAndDeletedFalse(Long id, String searchText, Pageable pageable);

	List<News> findByProductIdAndNameContainsAndDeletedFalse(Long id, String searchText);

	Page<News> findByProductIdAndDeletedFalse(Long id, Pageable pageable);

	List<News> findByProductIdAndDeletedFalse(Long id);

	Page<News> findByNameContainsAndDeletedFalse(String searchText, Pageable pageable);
	
	List<News> findByNameContainsAndDeletedFalse(String searchText);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE News p SET p.is_deleted=1 WHERE p.id = ?1", nativeQuery = true)
	void deleteById(Long id);

	News findById(Long id);

	News findByUrl(String url);
	
	Page<News> findAll(Pageable pageable);
	
	List<News> findAll();
	
	Integer countByDeletedFalse();
	
	Page<News> findByDeletedFalse(Pageable pageable);

	List<News> findByDeletedFalse();
	
	List<News> findByUrlAndIdNot(Long id, String url);

	List<News> findByUrlAndDeletedFalse(String url);
	
	List<News> findByProductId(Long id);

	List<News> findTop4ByDeletedFalseOrderByCreatedDateDesc();
	
}
