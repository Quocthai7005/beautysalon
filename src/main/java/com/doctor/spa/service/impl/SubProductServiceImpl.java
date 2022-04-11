package com.doctor.spa.service.impl;

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
import com.doctor.spa.repository.ProductRepo;
import com.doctor.spa.repository.SubProductRepo;
import com.doctor.spa.service.AwsS3Service;
import com.doctor.spa.service.SubProductService;

@Service
@Transactional
public class SubProductServiceImpl implements SubProductService{
	
	@Autowired
	SubProductRepo subProductRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	SubProductMapper subProductMapper;
	
	@Autowired
	AwsS3Service imageService;

	@Override
	public List<SubProductDto> getHomeShownChildService() {
		return subProductRepo.getByIsShownHomeTrue()
			.stream()
			.map(x -> {
				x.setUrl(x.getParentProduct().getUrl() + "/" + x.getUrl());
				return x;
			})
			.map(subProductMapper::toDto)
			.collect(Collectors.toList());
	}

	@Override
	public SubProductDto getChildServiceByUrl(String url) {
		return subProductMapper.toDto(subProductRepo.findByUrl(url));
	}

	@Override
	public List<SubProductDto> getChildServiceOtherThan(String serviceUrl, String childServiceUrl) {
		return productRepo.findByUrl(serviceUrl).getSubProducts()
			.stream()
			.filter(s -> (!s.getUrl().equals(childServiceUrl) && !s.isDeleted()))
			.map(subProductMapper::toDto)
			.collect(Collectors.toList());
	}

	@Override
	public long getServiceNo(Long id) {
		if (id == 0 || id == null) {
			return subProductRepo.findByDeletedFalse().size();
		} else {
			return subProductRepo.findTop4ByParentProductIdAndDeletedFalse(id).size();
		}
	}

	@Override
	public Page<SubProductDto> getChildServiceByGroupId(Long id, Pageable pageable) {
		Page<SubProduct> services;
		if (id == 0 || id == null) {
			services = subProductRepo.findByDeletedFalse(pageable);
		} else {
			services = subProductRepo.findByParentProductIdAndDeleted(id, false, pageable);
		}
		return new PageImpl<>(services.getContent()
				.stream()
				.map(subProductMapper::toDto)
				.collect(Collectors.toList()));
	}

	@Override
	@Transactional
	public Boolean deleteService(Long id) {
		if (id == null) {
			return false;
		}
		subProductRepo.deleteById(id);
		return true;
	}

	@Override
	@Transactional
	public Boolean createService(SubProductDto dto, MultipartFile image) {
		try {
			SubProduct service = new SubProduct();
			service.setName(dto.getName());
			service.setImage(imageService.uploadFile(image));
			service.setContent(dto.getContent());
			service.setIntro(dto.getIntro());
			service.setParentProduct(productRepo.findById(dto.getParentServiceId()));
			service.setShownHome(dto.getIsShownHome());
			subProductRepo.save(service);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public Boolean updateService(SubProductDto dto) {
		try {
			SubProduct service = subProductRepo.findById(dto.getId());
			if (service != null) {
				com.doctor.spa.entity.Product pService = productRepo.findById(dto.getParentServiceId());
				service.setName(dto.getName());
				service.setImage(dto.getImageKey());
				service.setContent(dto.getContent());
				service.setIntro(dto.getIntro());
				service.setParentProduct(pService);
				service.setShownHome(dto.getIsShownHome());
				subProductRepo.save(service);
			}
		} catch(Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public Map<String, Boolean> validateUrlNoId(String url) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		List<SubProduct> services = subProductRepo.findByUrlByDeletedFalse(url);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}

	@Override
	public SubProductDto getChildServiceById(long id) {
		SubProduct service = subProductRepo.findById(id);
		SubProductDto serviceDto = new SubProductDto(); 
		if (service != null) {
			serviceDto = subProductMapper.toDto(service);
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
		List<SubProduct> services = subProductRepo.findByUrlByIdNotEqualByDeletedFalse(url, id);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}

	@Override
	public List<SubProductDto> getAll() {
		return subProductRepo.findAll().
				stream()
				.map(subProductMapper::toDto)
				.collect(Collectors.toList());
	}

}
