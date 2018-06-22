package com.doctor.spa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.configuration.AjaxTimeoutRedirectFilter;
import com.doctor.spa.dto.ServiceGroupDto;
import com.doctor.spa.entity.ChildService;
import com.doctor.spa.mapper.ServiceGroupMapper;
import com.doctor.spa.repository.ChildServiceRepo;
import com.doctor.spa.repository.ServiceGroupRepo;
import com.doctor.spa.service.ServiceGroupService;

@Service
@Transactional(readOnly = true)
public class ServiceGroupServiceImpl implements ServiceGroupService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxTimeoutRedirectFilter.class);
	
	@Autowired
	ServiceGroupRepo serviceRepo;
	
	@Autowired
	ChildServiceRepo childServiceRepo;
	
	@Autowired
	ServiceGroupMapper serviceMapper;

	@Override
	public List<ServiceGroupDto> getAllServices() {	
		List<com.doctor.spa.entity.ServiceGroup> services = this.serviceRepo.findByDeletedFalse();
		List<ServiceGroupDto> serviceDtos = services.stream().map(service -> serviceMapper.toDto(service)).collect(Collectors.toList());
		return serviceDtos;
	}

	@Override
	public ServiceGroupDto getServiceByUrl(String url) {
		com.doctor.spa.entity.ServiceGroup service = serviceRepo.findByUrl(url);
		ServiceGroupDto dto = serviceMapper.toDto(service);
		return dto;
	}

	@Override
	public List<ServiceGroupDto> getServiceOtherThan(String url) {
		List<com.doctor.spa.entity.ServiceGroup> services = serviceRepo.findByDeletedFalseAndUrlNotLike(url);
		List<ServiceGroupDto> servicesDto = services.stream().map(service -> serviceMapper.toDto(service)).collect(Collectors.toList());
		return servicesDto;
	}

	@Override
	public Page<ServiceGroupDto> getServices(Pageable pageable) {
		Page<com.doctor.spa.entity.ServiceGroup> services = new PageImpl<>(Collections.emptyList());
		services = serviceRepo.findByDeletedFalse(pageable);
		List<ServiceGroupDto> serviceDtos = new ArrayList<ServiceGroupDto>();
		services.getContent().forEach(service -> {
			ServiceGroupDto dto = serviceMapper.toDto(service);
			serviceDtos.add(dto);
		});
		return new PageImpl<ServiceGroupDto>(serviceDtos);
	}
	
	@Override
	public Integer getServiceNo() {	
		Integer serviceNo = serviceRepo.countByDeletedFalse();
		return serviceNo;	
	}

	@Override
	@Transactional
	public Boolean deleteService(Long id) {
		if (id == null) {
			return false;
		}
		List<ChildService> childServices = childServiceRepo.findByServiceGroupIdByDeletedFalse(id);
		if (childServices.size() > 0) {
			return false;
		} else {
			serviceRepo.deleteByIdDeletedFalse(id);
			LOGGER.info("Service with id: " + id + " deleted");
			return true;
		}
	}

	@Override
	@Transactional
	public Boolean createService(ServiceGroupDto dto) {
		if (dto == null) {
			return false;
		}
		com.doctor.spa.entity.ServiceGroup service = serviceMapper.toEntity(dto);
		serviceRepo.save(service);
		return true;
	}

	@Override
	public ServiceGroupDto getServiceById(long id) {
		com.doctor.spa.entity.ServiceGroup service = serviceRepo.findById(id);
		ServiceGroupDto dto = serviceMapper.toDto(service);
		return dto;
	}

	@Override
	@Transactional
	public Boolean updateService(ServiceGroupDto serviceDto) {
		// Get service
		if (serviceDto == null) {
			return false;
		}
		if (serviceDto.getId() == null) {
			return false;
		}
		com.doctor.spa.entity.ServiceGroup service = serviceRepo.findById(serviceDto.getId());
		service.setImage(serviceDto.getImage());
		service.setName(serviceDto.getName());
		service.setUrl(serviceDto.getUrl());
		service.setContent(serviceDto.getContent());
		serviceRepo.save(service);
		// Save
		return true;
	}

	@Override
	public Map<String, Boolean> validateUrl(Long id, String url) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		if (id == null) {
			result.put("valid", false);
			return result;
		}
		List<com.doctor.spa.entity.ServiceGroup> services = serviceRepo.findByUrlByIdNotEqual(url, id);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}

	@Override
	public Map<String, Boolean> validateUrlNoId(String url) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		List<com.doctor.spa.entity.ServiceGroup> services = serviceRepo.findServicesByUrl(url);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}
}
