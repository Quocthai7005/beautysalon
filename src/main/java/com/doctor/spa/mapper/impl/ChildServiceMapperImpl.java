package com.doctor.spa.mapper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.entity.SubProduct;
import com.doctor.spa.mapper.SubProductMapper;

@Service
public class ChildServiceMapperImpl implements SubProductMapper {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	@Override
	public SubProductDto toDto(SubProduct childService) {
		SubProductDto dto = new SubProductDto();
		if (childService != null) {
			dto.setContent(childService.getContent());
			dto.setCreatedDate(childService.getCreatedDate().toString());
			dto.setId(childService.getId());
			dto.setImage(amazonS3.getUrl(bucketName, childService.getImage()).toString());
			dto.setIntro(childService.getIntro());
			dto.setName(childService.getName());
			dto.setParentServiceId(childService.getParentService().getId());
			dto.setUpdatedDate(childService.getUpdatedDate().toString());
			dto.setUrl(childService.getUrl());
			dto.setParentServiceName(childService.getParentService().getName());
			dto.setIsShownHome(childService.isShownHome());
		} else {
			dto = null;
		}	
		return dto;
	}

}
