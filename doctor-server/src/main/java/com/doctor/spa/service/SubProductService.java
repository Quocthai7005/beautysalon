package com.doctor.spa.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.dto.SubProductDto;

public interface SubProductService {
	
	List<SubProductDto> getHomeShownChildService();
	
	SubProductDto getSubProductByUrl(String url);

	List<SubProductDto> getSubProductOtherThan(String serviceUrl, String childServiceUrl);
	
	long getProductNo(Long id);
	
	Page<SubProductDto> getSubProductByGroupId(Long id, Pageable pageable);
	
	Boolean deleteSubProduct(Long id);
	
	SubProductDto createSubProduct(SubProductDto serviceDto, MultipartFile image);
	
	Boolean updateSubProduct(SubProductDto serviceDto);
	
	Map<String, Boolean> validateUrlNoId(String url);
	
	Map<String, Boolean> validateUrl(String url, Long id);
	
	SubProductDto getSubProductById(long id);
	
	List<SubProductDto> getAll();
}
