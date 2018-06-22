package com.doctor.spa.mapper.impl;

import org.springframework.stereotype.Service;

import com.doctor.spa.dto.PostDto;
import com.doctor.spa.entity.Post;
import com.doctor.spa.mapper.PostMapper;

@Service
public class PostMapperImpl implements PostMapper {

	@Override
	public PostDto toDto(Post news) {
		PostDto dto = null;
		if (news != null) {
			dto = new PostDto();
			dto.setId(news.getId());
			dto.setImage(news.getImage());
			dto.setContent(news.getContent());
			dto.setIntro(news.getIntro());
			dto.setName(news.getName());
			dto.setCreatedDate(news.getCreatedDate().toString());
			dto.setUpdatedDate(news.getCreatedDate().toString());
			dto.setUrl(news.getUrl());
			dto.setParentServiceId(news.getService().getId());
			dto.setParentServiceName(news.getService().getName());
		}
		return dto;
	}

}
