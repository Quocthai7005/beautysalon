package com.doctor.spa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.entity.Post;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
	
	// Admin's query

	Page<Post> findByProductIdAndNameContainsAndDeletedFalse(Long id, String searchText, Pageable pageable);

	List<Post> findByProductIdAndNameContainsAndDeletedFalse(Long id, String searchText);

	Page<Post> findByProductIdAndDeletedFalse(Long id, Pageable pageable);

	List<Post> findByProductIdAndDeletedFalse(Long id);

	Page<Post> findByNameContainsAndDeletedFalse(String searchText, Pageable pageable);
	
	List<Post> findByNameContainsAndDeletedFalse(String searchText);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE News p SET p.is_deleted=1 WHERE p.id = ?1", nativeQuery = true)
	void deleteById(Long id);

	Post findById(Long id);

	Post findByUrl(String url);
	
	Page<Post> findAll(Pageable pageable);
	
	List<Post> findAll();
	
	Integer countByDeletedFalse();
	
	Page<Post> findByDeletedFalse(Pageable pageable);

	List<Post> findByDeletedFalse();
	
	List<Post> findByUrlAndIdNot(Long id, String url);

	List<Post> findByUrlAndDeletedFalse(String url);
	
	List<Post> findByProductId(Long id);

	List<Post> findTop4ByDeletedFalseOrderByCreatedDateDesc();
	
}
