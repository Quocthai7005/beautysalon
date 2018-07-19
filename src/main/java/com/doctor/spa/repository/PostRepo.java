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
public interface PostRepo extends PagingAndSortingRepository<Post, Long> {
	
	// Admin's query
	
	@Query(value = "SELECT p FROM Post p WHERE p.service.id = ?1 AND p.name LIKE %?2% AND p.deleted = 0")
	Page<Post> findByServiceGroupIdBySearchTextByDeletedFalse(Long id, String searchText, Pageable pageable);

	@Query(value = "SELECT p FROM Post p WHERE p.service.id = ?1 AND p.name LIKE %?2% AND p.deleted = 0")
	List<Post> findByServiceGroupIdBySearchTextByDeletedFalse(Long id, String searchText);
	
	@Query(value = "SELECT p FROM Post p WHERE p.service.id = ?1 AND p.deleted = 0")
	Page<Post> findByServiceGroupIdByDeletedFalse(Long id, Pageable pageable);
	
	@Query(value = "SELECT p FROM Post p WHERE p.service.id = ?1 AND p.deleted = 0")
	List<Post> findByServiceGroupIdByDeletedFalse(Long id);
	
	@Query(value = "SELECT p FROM Post p WHERE p.name LIKE %?1% AND p.deleted = 0")
	Page<Post> findBySearchTextByDeletedFalse(String searchText, Pageable pageable);
	
	@Query(value = "SELECT * FROM Post p WHERE p.name LIKE %?1% AND p.is_deleted = 0", nativeQuery = true)
	List<Post> findBySearchTextByDeletedFalse(String searchText);
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Post p SET p.is_deleted=1 WHERE p.id = ?1", nativeQuery = true)
	void deleteById(Long id);
	
	// Others' query
	
	Post findById(Long id);
	
	@Query(value = "SELECT * FROM Post p WHERE p.url LIKE ?1 AND p.is_deleted = 0", nativeQuery = true)
	Post findByUrl(String url);
	
	Page<Post> findAll(Pageable pageable);
	
	Integer countByDeletedFalse();
	
	Page<Post> findByDeletedFalse(Pageable pageable);

	List<Post> findByDeletedFalse();
	
	@Query(value = "SELECT * FROM Post p WHERE p.url LIKE ?2 AND p.id != ?1 AND p.is_deleted = 0", nativeQuery = true)
	List<Post> findByUrlByIdNotEqual(Long id, String url);
	
	@Query(value = "SELECT * FROM Post p WHERE p.url LIKE ?1 AND p.is_deleted = 0", nativeQuery = true)
	List<Post> findByUrlByDeletedFalse(String url);
	
	List<Post> findByServiceId(Long id);
	
	@Query(value = "SELECT * FROM Post p WHERE p.is_deleted = 0 ORDER BY p.created_date LIMIT 4 ", nativeQuery = true)
	List<Post> findFirst4ByUrlNotLikeOrderByCreatedDateDesc();
	
}
