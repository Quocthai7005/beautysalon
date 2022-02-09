package com.doctor.spa.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.dto.ProductDto;
import com.doctor.spa.entity.SubProduct;
import com.doctor.spa.entity.News;
import com.doctor.spa.mapper.SubProductMapper;
import com.doctor.spa.mapper.NewsMapper;
import com.doctor.spa.mapper.ProductMapper;
import com.doctor.spa.repository.NewsRepo;

@Service
public class ServiceMapperImpl implements ProductMapper {
	
	@Autowired
	private AmazonS3 amazonS3;
	
	@Value("${aws.s3.bucket.name}")
	private String bucketName;
	
	@Autowired
	SubProductMapper childServiceMapper;
	
	@Autowired
	NewsRepo newsRepo;
	
	@Autowired
	NewsMapper newsMapper;

	@Override
	public ProductDto toDto(com.doctor.spa.entity.Product service) {
		ProductDto dto = null;
		if (service != null) {
			dto = new ProductDto();
			dto.setId(service.getId());
			dto.setContent(service.getContent());
			dto.setCreatedDate(service.getCreatedDate().toString());
			dto.setImage(amazonS3.getUrl(bucketName, service.getImage()).toString());
			dto.setIntro(service.getIntro());
			dto.setName(service.getName());
			dto.setUpdatedDate(service.getUpdatedDate().toString());
			dto.setUrl(service.getUrl());
			
			List<SubProductDto> childServiceDtos = new ArrayList<SubProductDto>();
			service.getChildServices().forEach(childService -> {
				if (childService.isDeleted() == false) {
					SubProductDto childServiceDto = childServiceMapper.toDto(childService);
					childServiceDtos.add(childServiceDto);
				}
			});
			dto.setChildServices(childServiceDtos);
			
			List<News> news = newsRepo.findByServiceId(service.getId());
			List<NewsDto> newsPosts = new ArrayList<NewsDto>();
			news.forEach(newsPost -> {
				NewsDto newsDto = newsMapper.toDto(newsPost);
				newsPosts.add(newsDto);
			});
			dto.setNews(newsPosts);
		}
		return dto;
	}

	@Override
	public com.doctor.spa.entity.Product toEntity(ProductDto dto) {
		com.doctor.spa.entity.Product service = new com.doctor.spa.entity.Product();
		service.setContent(dto.getContent());
		service.setChildServices(new ArrayList<SubProduct>());
		service.setImage(dto.getImage());
		service.setIntro(dto.getIntro());
		service.setName(dto.getName());
		service.setNews(new ArrayList<News>());
		service.setUrl(dto.getUrl());
		return service;
	}

}
