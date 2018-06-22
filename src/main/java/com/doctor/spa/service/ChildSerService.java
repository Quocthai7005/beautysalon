package com.doctor.spa.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.doctor.spa.dto.ChildServiceDto;

public interface ChildSerService {
	
	List<ChildServiceDto> getHomeShownChildService();
	
	ChildServiceDto getChildServiceByUrl(String url);

	List<ChildServiceDto> getChildServiceOtherThan(String serviceUrl, String childServiceUrl);
	
	Integer getServiceNo(Long id);
	
	Page<ChildServiceDto> getChildServiceByGroupId(Long id, Pageable pageable);
	
	Boolean deleteService(Long id);
	
	Boolean createService(ChildServiceDto serviceDto);
	
	Boolean updateService(ChildServiceDto serviceDto);
	
	Map<String, Boolean> validateUrlNoId(String url);
	
	Map<String, Boolean> validateUrl(String url, Long id);
	
	ChildServiceDto getChildServiceById(long id);
}
