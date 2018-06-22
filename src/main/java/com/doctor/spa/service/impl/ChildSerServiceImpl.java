package com.doctor.spa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.dto.ChildServiceDto;
import com.doctor.spa.entity.ChildService;
import com.doctor.spa.mapper.ChildServiceMapper;
import com.doctor.spa.repository.ChildServiceRepo;
import com.doctor.spa.repository.ServiceGroupRepo;
import com.doctor.spa.service.ChildSerService;

@Service
@Transactional
public class ChildSerServiceImpl implements ChildSerService{
	
	@Autowired
	ChildServiceRepo childServiceRepo;
	
	@Autowired
	ServiceGroupRepo serviceRepo;
	
	@Autowired
	ChildServiceMapper childServiceMapper;

	@Override
	public List<ChildServiceDto> getHomeShownChildService() {
		List<ChildService> services = childServiceRepo.getByIsShownHomeTrue();
		List<ChildServiceDto> serviceDtos = new ArrayList<ChildServiceDto>();
		services.forEach(service -> {
			service.setUrl(service.getParentService().getUrl() + "/" + service.getUrl());
			ChildServiceDto serviceDto = childServiceMapper.toDto(service);
			serviceDtos.add(serviceDto);
		});
		return serviceDtos;
	}

	@Override
	public ChildServiceDto getChildServiceByUrl(String url) {
		ChildService childService = childServiceRepo.findByUrl(url);
		ChildServiceDto childServiceDto = childServiceMapper.toDto(childService);
		return childServiceDto;
	}

	@Override
	public List<ChildServiceDto> getChildServiceOtherThan(String serviceUrl, String childServiceUrl) {
		com.doctor.spa.entity.ServiceGroup service = serviceRepo.findByUrl(serviceUrl);
		List<ChildService> childServices = service.getChildServices().stream().filter(s -> s.getUrl() != childServiceUrl).collect(Collectors.toList());
		List<ChildServiceDto> serviceDtos = new ArrayList<ChildServiceDto>();
		childServices.forEach(childService -> {
			ChildServiceDto serviceDto = childServiceMapper.toDto(childService);
			serviceDtos.add(serviceDto);
		});
		return serviceDtos;
	}

	@Override
	public Integer getServiceNo(Long id) {
		List<ChildService> services = new ArrayList<ChildService>();
		if (id == 0 || id == null) {
			services = childServiceRepo.findByDeletedFalse();
		} else {
			services = childServiceRepo.findByServiceGroupIdByDeletedFalse(id);
		}
		System.out.println(services.size());
		return services.size();
	}

	@Override
	public Page<ChildServiceDto> getChildServiceByGroupId(Long id, Pageable pageable) {
		Page<ChildService> services = new PageImpl<>(Collections.emptyList());
		if (id == 0 || id == null) {
			services = childServiceRepo.findByDeletedFalse(pageable);
		} else {
			services = childServiceRepo.findByServiceGroupIdByDeletedFalse(id, pageable);
		}
		List<ChildServiceDto> serviceDtos = new ArrayList<ChildServiceDto>();
		services.getContent().forEach(service -> {
			ChildServiceDto dto = childServiceMapper.toDto(service);
			serviceDtos.add(dto);
		});
		return new PageImpl<ChildServiceDto>(serviceDtos);
	}

	@Override
	@Transactional
	public Boolean deleteService(Long id) {
		if (id == null) {
			return false;
		}
		childServiceRepo.deleteById(id);
		return true;
	}

	@Override
	@Transactional
	public Boolean createService(ChildServiceDto dto) {
		if (dto == null) {
			return false;
		}
		try {
			ChildService service = new ChildService();
			com.doctor.spa.entity.ServiceGroup pService = serviceRepo.findById(dto.getParentServiceId());
			service.setName(dto.getName());
			service.setImage(dto.getImage());
			service.setContent(dto.getContent());
			service.setIntro(dto.getIntro());
			service.setParentService(pService);
			service.setShownHome(dto.getIsShownHome());
			childServiceRepo.save(service);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public Boolean updateService(ChildServiceDto dto) {
		if (dto == null) {
			return false;
		}
		try {
			ChildService service = childServiceRepo.findById(dto.getId());
			if (service != null) {
				com.doctor.spa.entity.ServiceGroup pService = serviceRepo.findById(dto.getParentServiceId());
				service.setName(dto.getName());
				service.setImage(dto.getImage());
				service.setContent(dto.getContent());
				service.setIntro(dto.getIntro());
				service.setParentService(pService);
				service.setShownHome(dto.getIsShownHome());
				childServiceRepo.save(service);
			}
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Map<String, Boolean> validateUrlNoId(String url) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		List<ChildService> services = childServiceRepo.findByUrlByDeletedFalse(url);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}

	@Override
	public ChildServiceDto getChildServiceById(long id) {
		ChildService service = childServiceRepo.findById(id);
		ChildServiceDto serviceDto = new ChildServiceDto(); 
		if (service != null) {
			serviceDto = childServiceMapper.toDto(service);
		}
		return serviceDto;
	}

	@Override
	public Map<String, Boolean> validateUrl(String url, Long id) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		if (id == null) {
			result.put("valid", false);
			return result;
		}
		List<ChildService> services = childServiceRepo.findByUrlByIdNotEqualByDeletedFalse(url, id);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}

}
