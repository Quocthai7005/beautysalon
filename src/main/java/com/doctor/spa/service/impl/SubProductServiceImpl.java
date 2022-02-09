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
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.entity.SubProduct;
import com.doctor.spa.mapper.SubProductMapper;
import com.doctor.spa.repository.SubProductRepo;
import com.doctor.spa.repository.ProductRepo;
import com.doctor.spa.service.SubProductService;
import com.doctor.spa.service.ImageService;

@Service
@Transactional
public class SubProductServiceImpl implements SubProductService{
	
	@Autowired
	SubProductRepo childServiceRepo;
	
	@Autowired
	ProductRepo serviceRepo;
	
	@Autowired
	SubProductMapper childServiceMapper;
	
	@Autowired
	ImageService imageService;

	@Override
	public List<SubProductDto> getHomeShownChildService() {
		List<SubProduct> services = childServiceRepo.getByIsShownHomeTrue();
		List<SubProductDto> serviceDtos = new ArrayList<SubProductDto>();
		services.forEach(service -> {
			service.setUrl(service.getParentService().getUrl() + "/" + service.getUrl());
			SubProductDto serviceDto = childServiceMapper.toDto(service);
			serviceDtos.add(serviceDto);
		});
		return serviceDtos;
	}

	@Override
	public SubProductDto getChildServiceByUrl(String url) {
		SubProduct childService = childServiceRepo.findByUrl(url);
		SubProductDto childServiceDto = childServiceMapper.toDto(childService);
		return childServiceDto;
	}

	@Override
	public List<SubProductDto> getChildServiceOtherThan(String serviceUrl, String childServiceUrl) {
		com.doctor.spa.entity.Product service = serviceRepo.findByUrl(serviceUrl);
		List<SubProduct> childServices = service.getChildServices().stream().filter(s -> (s.getUrl() != childServiceUrl && s.isDeleted() == false)).collect(Collectors.toList());
		List<SubProductDto> serviceDtos = new ArrayList<SubProductDto>();
		childServices.forEach(childService -> {
			SubProductDto serviceDto = childServiceMapper.toDto(childService);
			serviceDtos.add(serviceDto);
		});
		return serviceDtos;
	}

	@Override
	public Integer getServiceNo(Long id) {
		List<SubProduct> services = new ArrayList<SubProduct>();
		if (id == 0 || id == null) {
			services = childServiceRepo.findByDeletedFalse();
		} else {
			services = childServiceRepo.findFirst4BySubProductIdByDeletedFalse(id);
		}
		System.out.println(services.size());
		return services.size();
	}

	@Override
	public Page<SubProductDto> getChildServiceByGroupId(Long id, Pageable pageable) {
		Page<SubProduct> services = new PageImpl<>(Collections.emptyList());
		if (id == 0 || id == null) {
			services = childServiceRepo.findByDeletedFalse(pageable);
		} else {
			services = childServiceRepo.findBySubProductIdByDeletedFalse(id, pageable);
		}
		List<SubProductDto> serviceDtos = new ArrayList<SubProductDto>();
		services.getContent().forEach(service -> {
			SubProductDto dto = childServiceMapper.toDto(service);
			serviceDtos.add(dto);
		});
		return new PageImpl<SubProductDto>(serviceDtos);
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
	public Boolean createService(SubProductDto dto, MultipartFile image) {
		if (dto == null) {
			return false;
		}
		try {
			SubProduct service = new SubProduct();
			com.doctor.spa.entity.Product pService = serviceRepo.findById(dto.getParentServiceId());
			service.setName(dto.getName());
			service.setImage(imageService.uploadFile(image));
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
	public Boolean updateService(SubProductDto dto) {
		if (dto == null) {
			return false;
		}
		try {
			SubProduct service = childServiceRepo.findById(dto.getId());
			if (service != null) {
				com.doctor.spa.entity.Product pService = serviceRepo.findById(dto.getParentServiceId());
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
		List<SubProduct> services = childServiceRepo.findByUrlByDeletedFalse(url);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}

	@Override
	public SubProductDto getChildServiceById(long id) {
		SubProduct service = childServiceRepo.findById(id);
		SubProductDto serviceDto = new SubProductDto(); 
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
		List<SubProduct> services = childServiceRepo.findByUrlByIdNotEqualByDeletedFalse(url, id);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}

}
