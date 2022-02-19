package com.doctor.spa.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.entity.News;
import com.doctor.spa.mapper.NewsMapper;

@Service
public class NewsMapperImpl implements NewsMapper {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	@Override
	public NewsDto toDto(News news) {
		NewsDto dto = null;
		if (news != null) {
			dto = new NewsDto();
			dto.setId(news.getId());
			dto.setImageKey(news.getImage());
			dto.setImageBaseUrl("https://" + bucketName + ".s3.amazonaws.com/");
			dto.setContent(news.getContent());
			dto.setIntro(news.getIntro());
			dto.setName(news.getName());
			dto.setCreatedDate(news.getCreatedDate().toString());
			dto.setUpdatedDate(news.getCreatedDate().toString());
			dto.setUrl(news.getUrl());
			dto.setParentServiceId(news.getProduct().getId());
			dto.setParentServiceName(news.getProduct().getName());
		}
		return dto;
	}

}
