package com.doctor.spa.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.entity.SubProduct;

public interface SubProductRepo extends JpaRepository<SubProduct, Long>{

	// Admin's query

	@Query(value = "SELECT * FROM subproduct cs WHERE cs.parent_product_id = ?1 AND cs.is_deleted = 0 LIMIT 4", nativeQuery = true)
	List<SubProduct> findTop4ByParentProductIdAndDeletedFalse(Long id);

	//@Query(value = "SELECT cs FROM subproduct cs WHERE cs.parent_product_id = ?1 AND cs.is_deleted = 0")
	Page<SubProduct> findByParentProductIdAndDeleted(Long id, Boolean isDeleted, Pageable pageable);

	@Query(value = "SELECT * FROM subproduct s WHERE s.url LIKE ?1 AND s.id != ?2 AND s.is_deleted = 0", nativeQuery = true)
	List<SubProduct> findByUrlByIdNotEqualByDeletedFalse(String url, Long id);

	@Modifying
	@Transactional
	@Query(value = "UPDATE subproduct s SET s.is_deleted=1 WHERE s.id = ?1", nativeQuery = true)
	void deleteById(Long id);

	List<SubProduct> getByIsShownHomeTrue();

	@Query(value = "SELECT * FROM subproduct s WHERE s.url LIKE ?1 AND s.is_deleted = 0", nativeQuery = true)
	List<SubProduct> findByUrlByDeletedFalse(String url);

	SubProduct findById(Long id);

	SubProduct findByUrl(String url);

	Page<SubProduct> findByDeletedFalse(Pageable pageable);

	List<SubProduct> findByDeletedFalse();
}
