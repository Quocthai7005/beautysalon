package com.doctor.spa.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.doctor.spa.dto.ServiceGroupDto;

public interface ServiceGroupService {
	
	List<ServiceGroupDto> getAllServices();
	
	Integer getServiceNo();
	
	ServiceGroupDto getServiceByUrl(String url);
	
	ServiceGroupDto getServiceById(long id);
	
	List<ServiceGroupDto> getServiceOtherThan(String url);
	
	Page<ServiceGroupDto> getServices(Pageable pageable);
	
	Boolean deleteService(Long id);
	
	Boolean createService(ServiceGroupDto serviceDto);
	
	Boolean updateService(ServiceGroupDto serviceDto);
	
	Map<String, Boolean> validateUrl(Long id, String url);

	Map<String, Boolean> validateUrlNoId(String url);
}
