package com.doctor.spa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
	
	// Admin's query
	List<Product> findByDeletedFalse();
	
	Page<Product> findByDeletedFalse(Pageable pageable);
	
	Integer countByDeletedFalse();
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE product s SET s.is_deleted=1 WHERE s.id = ?1", nativeQuery = true)
	void deleteByIdDeletedFalse(Long id);
	
	// Others' query
	
	Product findById(Long id);

	Product findByUrl(String url);

	List<Product> findByDeletedFalseAndUrlNotLike(String url);
	
	@Query(value = "SELECT * FROM product s WHERE s.url LIKE ?1 AND s.id != ?2 AND s.is_deleted = 0", nativeQuery = true)
	List<Product> findByUrlByIdNotEqual(String url, Long id);
	
	@Query(value = "SELECT * FROM product s WHERE s.url LIKE ?1 AND s.is_deleted = 0", nativeQuery = true)
	List<Product> findProductsByUrl(String url);
}
