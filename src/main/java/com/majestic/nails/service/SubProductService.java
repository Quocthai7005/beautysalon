package com.majestic.nails.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.majestic.nails.dto.SubProductDto;

public interface SubProductService {
	
	List<SubProductDto> getHomeShownChildService();
	
	SubProductDto getChildServiceByUrl(String url);

	List<SubProductDto> getChildServiceOtherThan(String serviceUrl, String childServiceUrl);
	
	long getServiceNo(Long id);
	
	Page<SubProductDto> getChildServiceByGroupId(Long id, Pageable pageable);
	
	Boolean deleteService(Long id);
	
	SubProductDto createService(SubProductDto serviceDto, MultipartFile image);
	
	Boolean updateService(SubProductDto serviceDto);
	
	Map<String, Boolean> validateUrlNoId(String url);
	
	Map<String, Boolean> validateUrl(String url, Long id);
	
	SubProductDto getChildServiceById(long id);
	
	List<SubProductDto> getAll();

	LinkedHashMap<String, List<SubProductDto>> getAllAndGroupByParent();
}
