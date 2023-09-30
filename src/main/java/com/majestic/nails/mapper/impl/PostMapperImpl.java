package com.majestic.nails.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.majestic.nails.dto.PostDto;
import com.majestic.nails.entity.Post;
import com.majestic.nails.mapper.PostMapper;

@Service
public class PostMapperImpl implements PostMapper {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	@Override
	public PostDto toDto(Post post) {
		PostDto dto = null;
		if (post != null) {
			dto = new PostDto();
			dto.setId(post.getId());
			dto.setImageKey(post.getImage());
			dto.setImageBaseUrl("https://" + bucketName + ".s3.amazonaws.com/");
			dto.setContent(post.getContent());
			dto.setIntro(post.getIntro());
			dto.setName(post.getName());
			dto.setCreatedDate(post.getCreatedDate().toString());
			dto.setUpdatedDate(post.getCreatedDate().toString());
			dto.setUrl(post.getUrl());
			dto.setParentServiceId(post.getProduct().getId());
			dto.setParentServiceName(post.getProduct().getName());
		}
		return dto;
	}

}
