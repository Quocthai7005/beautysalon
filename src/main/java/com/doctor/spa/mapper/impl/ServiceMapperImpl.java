package com.doctor.spa.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctor.spa.dto.ChildServiceDto;
import com.doctor.spa.dto.PostDto;
import com.doctor.spa.dto.ServiceGroupDto;
import com.doctor.spa.entity.ChildService;
import com.doctor.spa.entity.Post;
import com.doctor.spa.mapper.ChildServiceMapper;
import com.doctor.spa.mapper.PostMapper;
import com.doctor.spa.mapper.ServiceGroupMapper;
import com.doctor.spa.repository.PostRepo;

@Service
public class ServiceMapperImpl implements ServiceGroupMapper {
	
	@Autowired
	ChildServiceMapper childServiceMapper;
	
	@Autowired
	PostRepo newsRepo;
	
	@Autowired
	PostMapper newsMapper;

	@Override
	public ServiceGroupDto toDto(com.doctor.spa.entity.ServiceGroup service) {
		ServiceGroupDto dto = null;
		if (service != null) {
			dto = new ServiceGroupDto();
			dto.setId(service.getId());
			dto.setContent(service.getContent());
			dto.setCreatedDate(service.getCreatedDate().toString());
			dto.setImage(service.getImage());
			dto.setIntro(service.getIntro());
			dto.setName(service.getName());
			dto.setUpdatedDate(service.getUpdatedDate().toString());
			dto.setUrl(service.getUrl());
			
			List<ChildServiceDto> childServiceDtos = new ArrayList<ChildServiceDto>();
			service.getChildServices().forEach(childService -> {
				if (childService.isDeleted() == false) {
					ChildServiceDto childServiceDto = childServiceMapper.toDto(childService);
					childServiceDtos.add(childServiceDto);
				}
			});
			dto.setChildServices(childServiceDtos);
			
			List<Post> news = newsRepo.findByServiceId(service.getId());
			List<PostDto> newsPosts = new ArrayList<PostDto>();
			news.forEach(newsPost -> {
				PostDto newsDto = newsMapper.toDto(newsPost);
				newsPosts.add(newsDto);
			});
			dto.setNews(newsPosts);
		}
		return dto;
	}

	@Override
	public com.doctor.spa.entity.ServiceGroup toEntity(ServiceGroupDto dto) {
		com.doctor.spa.entity.ServiceGroup service = new com.doctor.spa.entity.ServiceGroup();
		service.setContent(dto.getContent());
		service.setChildServices(new ArrayList<ChildService>());
		service.setImage(dto.getImage());
		service.setIntro(dto.getIntro());
		service.setName(dto.getName());
		service.setNews(new ArrayList<Post>());
		service.setUrl(dto.getUrl());
		return service;
	}

}
