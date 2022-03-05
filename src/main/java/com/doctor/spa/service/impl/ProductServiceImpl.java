package com.doctor.spa.service.impl;

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
import com.doctor.spa.mapper.ProductMapper;
import com.doctor.spa.repository.ProductRepo;
import com.doctor.spa.repository.SubProductRepo;
import com.doctor.spa.service.AwsS3Service;
import com.doctor.spa.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	SubProductRepo subProductRepo;
	
	@Autowired
	ProductMapper productMapper;

	@Autowired
	AwsS3Service imageService;

	@Override
	public List<ProductDto> getAllServices() {
		return this.productRepo.findByDeletedFalse()
				.stream()
				.map(productMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public ProductDto getServiceByUrl(String url) {
		return productMapper.toDto(productRepo.findByUrl(url));
	}

	@Override
	public List<ProductDto> getServiceOtherThan(String url) {
		return productRepo
				.findByDeletedFalseAndUrlNotLike(url)
				.map(productMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public Page<ProductDto> getServices(Pageable pageable) {
		return new PageImpl<>(
				productRepo.findByDeletedFalse(pageable)
				.getContent()
				.stream().map(productMapper::toDto)
				.collect(Collectors.toList()));
	}
	
	@Override
	public Integer getServiceNo() {	
		return productRepo.countByDeletedFalse();
	}

	@Override
	@Transactional
	public Boolean deleteService(Long id) {
		if (id == null) {
			return false;
		}
		if (subProductRepo.findFirst4BySubProductIdByDeletedFalse(id).count() > 0) {
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
		return productMapper.toDto(productRepo.findById(id));
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
		service.setImage(serviceDto.getImageKey());
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

	@Override
	public List<ProductDto> getAll() {
		return productRepo
				.findAll()
				.stream()
				.map(productMapper::toDto)
				.collect(Collectors.toList());
	}
}
