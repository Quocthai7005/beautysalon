package com.doctor.spa.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.doctor.spa.dto.PostDto;
import com.doctor.spa.entity.Post;
import com.doctor.spa.mapper.PostMapper;

@Service
public class PostMapperImpl implements PostMapper {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	@Override
	public PostDto toDto(Post news) {
		PostDto dto = null;
		if (news != null) {
			dto = new PostDto();
			dto.setId(news.getId());
			dto.setImage(amazonS3.getUrl(bucketName, news.getImage()).toString());
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
