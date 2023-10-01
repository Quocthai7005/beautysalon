package com.majestic.nails.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.majestic.nails.entity.Product;
import com.majestic.nails.mapper.ProductMapper;
import com.majestic.nails.repository.ProductRepository;
import com.majestic.nails.repository.SubProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.majestic.nails.dto.ProductDto;
import com.majestic.nails.service.AwsS3Service;
import com.majestic.nails.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final ProductRepository productRepository;
	private final SubProductRepository subProductRepository;
	private final ProductMapper productMapper;
	private final AwsS3Service imageService;

	public ProductServiceImpl(ProductRepository productRepository, SubProductRepository subProductRepository,
							  ProductMapper productMapper, AwsS3Service imageService) {
		this.productRepository = productRepository;
		this.subProductRepository = subProductRepository;
		this.productMapper = productMapper;
		this.imageService = imageService;
	}

	@Override
	public List<ProductDto> getAllServices() {
		return this.productRepository.findByDeletedFalse()
				.stream()
				.map(productMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public ProductDto getServiceByUrl(String url) {
		return productMapper.toDto(productRepository.findByUrl(url));
	}

	@Override
	@Transactional
	public List<ProductDto> getServiceOtherThan(String url) {
		return productRepository
				.findByDeletedFalseAndUrlNotLike(url)
				.stream()
				.map(productMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public Page<ProductDto> getServices(Pageable pageable) {
		return new PageImpl<>(
				productRepository.findByDeletedFalse(pageable)
				.getContent()
				.stream().map(productMapper::toDto)
				.collect(Collectors.toList()));
	}
	
	@Override
	public Integer getServiceNo() {	
		return productRepository.countByDeletedFalse();
	}

	@Override
	@Transactional
	public Boolean deleteService(Long id) {
		if (subProductRepository.findTop4ByParentProductIdAndDeletedFalse(id).size() > 0) {
			return false;
		} else {
			productRepository.deleteByIdDeletedFalse(id);
			LOGGER.info("Service with id: " + id + " deleted");
			return true;
		}
	}

	@Override
	@Transactional
	public Boolean createService(ProductDto dto, MultipartFile image) {
		Product service = productMapper.toEntity(dto);
		//String imageName = imageService.uploadFile(image);
		//service.setImage(imageName);
		productRepository.save(service);
		return true;
	}

	@Override
	public ProductDto getServiceById(long id) {
		return productMapper.toDto(productRepository.findById(id));
	}

	@Override
	@Transactional
	public ProductDto updateService(ProductDto serviceDto) {
		Product service = productRepository.findById(serviceDto.getId());
		service.setImage(serviceDto.getImageKey());
		service.setName(serviceDto.getName());
		service.setUrl(serviceDto.getUrl());
		service.setContent(serviceDto.getContent());
		productRepository.save(service);
		return serviceDto;
	}

	@Override
	public Map<String, Boolean> validateUrl(Long id, String url) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		List<Product> services = productRepository.findByUrlByIdNotEqual(url, id);
		Boolean isValid = services.isEmpty();	
		result.put("valid", id == null ? false : isValid);
		return result;
	}

	@Override
	public Map<String, Boolean> validateUrlNoId(String url) {
		Map<String, Boolean> result = new HashMap<String, Boolean>();
		List<Product> services = productRepository.findProductsByUrl(url);
		Boolean isValid = services.isEmpty();	
		result.put("valid", isValid);
		return result;
	}

	@Override
	public List<ProductDto> getAll() {
		return productRepository
				.findAll()
				.stream()
				.map(productMapper::toDto)
				.collect(Collectors.toList());
	}
}
