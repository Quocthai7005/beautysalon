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
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.configuration.AjaxTimeoutRedirectFilter;
import com.doctor.spa.dto.ProductDto;
import com.doctor.spa.entity.SubProduct;
import com.doctor.spa.mapper.ProductMapper;
import com.doctor.spa.repository.SubProductRepo;
import com.doctor.spa.repository.ProductRepo;
import com.doctor.spa.service.ImageService;
import com.doctor.spa.service.ProductService;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AjaxTimeoutRedirectFilter.class);
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	SubProductRepo subProductRepo;
	
	@Autowired
	ProductMapper productMapper;

	@Autowired
	ImageService imageService;

	@Override
	public List<ProductDto> getAllServices() {	
		List<com.doctor.spa.entity.Product> products = this.productRepo.findByDeletedFalse();
		List<ProductDto> productDtos = products
				.stream()
				.map(product -> productMapper.toDto(product)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public ProductDto getServiceByUrl(String url) {
		com.doctor.spa.entity.Product product = productRepo.findByUrl(url);
		ProductDto dto = productMapper.toDto(product);
		return dto;
	}

	@Override
	public List<ProductDto> getServiceOtherThan(String url) {
		List<com.doctor.spa.entity.Product> products = productRepo.findByDeletedFalseAndUrlNotLike(url);
		List<ProductDto> productDtos = products.stream().map(service -> productMapper.toDto(service)).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public Page<ProductDto> getServices(Pageable pageable) {
		Page<com.doctor.spa.entity.Product> services = new PageImpl<>(Collections.emptyList());
		services = productRepo.findByDeletedFalse(pageable);
		List<ProductDto> serviceDtos = new ArrayList<ProductDto>();
		services.getContent().forEach(service -> {
			ProductDto dto = productMapper.toDto(service);
			serviceDtos.add(dto);
		});
		return new PageImpl<ProductDto>(serviceDtos);
	}
	
	@Override
	public Integer getServiceNo() {	
		Integer serviceNo = productRepo.countByDeletedFalse();
		return serviceNo;	
	}

	@Override
	@Transactional
	public Boolean deleteService(Long id) {
		if (id == null) {
			return false;
		}
		List<SubProduct> childServices = subProductRepo.findFirst4BySubProductIdByDeletedFalse(id);
		if (childServices.size() > 0) {
			return false;
		} else {
			productRepo.deleteByIdDeletedFalse(id);
			LOGGER.info("Service with id: " + id + " deleted");
			return true;
		}
	}

	@Override
	@Transactional
	public Boolean createService(ProductDto dto, MultipartFile image) {
		if (dto == null) {
			return false;
		}
		com.doctor.spa.entity.Product service = productMapper.toEntity(dto);
		String imageName = imageService.uploadFile(image);
		service.setImage(imageName);
		productRepo.save(service);
		return true;
	}

	@Override
	public ProductDto getServiceById(long id) {
		com.doctor.spa.entity.Product service = productRepo.findById(id);
		ProductDto dto = productMapper.toDto(service);
		return dto;
	}

	@Override
	@Transactional
	public Boolean updateService(ProductDto serviceDto) {
		// Get service
		if (serviceDto == null) {
			return false;
		}
		if (serviceDto.getId() == null) {
			return false;
		}
		com.doctor.spa.entity.Product service = productRepo.findById(serviceDto.getId());
		service.setImage(serviceDto.getImage());
		service.setName(serviceDto.getName());
		service.setUrl(serviceDto.getUrl());
		service.setContent(serviceDto.getContent());
		productRepo.save(service);
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
		List<com.doctor.spa.entity.Product> services = productRepo.findByUrlByIdNotEqual(url, id);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}

	@Override
	public Map<String, Boolean> validateUrlNoId(String url) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		List<com.doctor.spa.entity.Product> services = productRepo.findProductsByUrl(url);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}
}