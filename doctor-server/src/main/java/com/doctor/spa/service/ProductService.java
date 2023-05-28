package com.doctor.spa.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.dto.ProductDto;

public interface ProductService {
	
	List<ProductDto> getAllServices();
	
	Integer getServiceNo();
	
	ProductDto getServiceByUrl(String url);
	
	ProductDto getServiceById(long id);
	
	List<ProductDto> getServiceOtherThan(String url);
	
	Page<ProductDto> getServices(Pageable pageable);
	
	Boolean deleteService(Long id);
	
	Boolean createService(ProductDto dto, MultipartFile image);
	
	ProductDto updateService(ProductDto serviceDto);
	
	Map<String, Boolean> validateUrl(Long id, String url);

	Map<String, Boolean> validateUrlNoId(String url);
	
	List<ProductDto> getAll();
}
