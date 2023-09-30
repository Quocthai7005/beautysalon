package com.doctor.spa.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import com.doctor.spa.entity.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.dto.PostDto;
import com.doctor.spa.dto.ProductDto;
import com.doctor.spa.entity.SubProduct;
import com.doctor.spa.mapper.SubProductMapper;
import com.doctor.spa.mapper.PostMapper;
import com.doctor.spa.mapper.ProductMapper;
import com.doctor.spa.repository.PostRepository;

@Service
public class ProductMapperImpl implements ProductMapper {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	@Autowired
	SubProductMapper childServiceMapper;

	@Autowired
	PostRepository postRepository;

	@Autowired
	PostMapper postMapper;

	@Override
	public ProductDto toDto(com.doctor.spa.entity.Product service) {
		ProductDto dto = null;
		if (service != null) {
			dto = new ProductDto();
			dto.setId(service.getId());
			dto.setContent(service.getContent());
			dto.setImageKey(service.getImage());
			dto.setImageBaseUrl("https://" + bucketName + ".s3.amazonaws.com/");
			dto.setIntro(service.getIntro());
			dto.setName(service.getName());
			dto.setUrl(service.getUrl());

			List<SubProductDto> childServiceDtos = new ArrayList<SubProductDto>();
			service.getSubProducts().forEach(childService -> {
				if (childService.isDeleted() == false) {
					SubProductDto childServiceDto = childServiceMapper.toDto(childService);
					childServiceDtos.add(childServiceDto);
				}
			});
			dto.setChildServices(childServiceDtos);

			List<Post> posts = postRepository.findByProductId(service.getId());
			List<PostDto> newsPosts = new ArrayList<PostDto>();
			posts.forEach(newsPost -> {
				PostDto newsDto = postMapper.toDto(newsPost);
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
		service.setSubProducts(new ArrayList<SubProduct>());
		service.setImage(dto.getImageKey());
		service.setIntro(dto.getIntro());
		service.setName(dto.getName());
		service.setNews(new ArrayList<Post>());
		service.setUrl(dto.getUrl());
		return service;
	}

}
