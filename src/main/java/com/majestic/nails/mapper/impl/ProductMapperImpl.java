package com.majestic.nails.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import com.majestic.nails.entity.Post;
import com.majestic.nails.entity.Product;
import com.majestic.nails.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.majestic.nails.dto.SubProductDto;
import com.majestic.nails.dto.PostDto;
import com.majestic.nails.dto.ProductDto;
import com.majestic.nails.entity.SubProduct;
import com.majestic.nails.mapper.SubProductMapper;
import com.majestic.nails.mapper.PostMapper;
import com.majestic.nails.mapper.ProductMapper;

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
	public ProductDto toDto(Product service) {
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
	public Product toEntity(ProductDto dto) {
		Product service = new Product();
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
