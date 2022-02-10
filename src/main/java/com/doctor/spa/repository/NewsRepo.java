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
	
	@Query(value = "SELECT p FROM News p WHERE p.product.id = ?1 AND p.name LIKE %?2% AND p.deleted = 0")
	Page<News> findByProductIdBySearchTextByDeletedFalse(Long id, String searchText, Pageable pageable);

	@Query(value = "SELECT p FROM News p WHERE p.product.id = ?1 AND p.name LIKE %?2% AND p.deleted = 0")
	List<News> findByProductIdBySearchTextByDeletedFalse(Long id, String searchText);
	
	@Query(value = "SELECT p FROM News p WHERE p.product.id = ?1 AND p.deleted = 0")
	Page<News> findByProductIdByDeletedFalse(Long id, Pageable pageable);
	
	@Query(value = "SELECT p FROM News p WHERE p.product.id = ?1 AND p.deleted = 0")
	List<News> findByProductIdByDeletedFalse(Long id);
	
	@Query(value = "SELECT p FROM News p WHERE p.name LIKE %?1% AND p.deleted = 0")
	Page<News> findBySearchTextByDeletedFalse(String searchText, Pageable pageable);
	
	@Query(value = "SELECT * FROM News p WHERE p.name LIKE %?1% AND p.is_deleted = 0", nativeQuery = true)
	List<News> findBySearchTextByDeletedFalse(String searchText);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE News p SET p.is_deleted=1 WHERE p.id = ?1", nativeQuery = true)
	void deleteById(Long id);
	
	// Others' query
	
	News findById(Long id);
	
	@Query(value = "SELECT * FROM News p WHERE p.url LIKE ?1 AND p.is_deleted = 0", nativeQuery = true)
	News findByUrl(String url);
	
	Page<News> findAll(Pageable pageable);
	
	Integer countByDeletedFalse();
	
	Page<News> findByDeletedFalse(Pageable pageable);

	List<News> findByDeletedFalse();
	
	@Query(value = "SELECT * FROM News p WHERE p.url LIKE ?2 AND p.id != ?1 AND p.is_deleted = 0", nativeQuery = true)
	List<News> findByUrlByIdNotEqual(Long id, String url);
	
	@Query(value = "SELECT * FROM News p WHERE p.url LIKE ?1 AND p.is_deleted = 0", nativeQuery = true)
	List<News> findByUrlByDeletedFalse(String url);
	
	List<News> findByProductId(Long id);
	
	@Query(value = "SELECT * FROM News p WHERE p.is_deleted = 0 ORDER BY p.created_date LIMIT 4 ", nativeQuery = true)
	List<News> findFirst4ByUrlNotLikeOrderByCreatedDateDesc();
	
}
