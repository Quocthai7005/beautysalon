package com.majestic.nails.mapper.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.majestic.nails.dto.SubProductDto;
import com.majestic.nails.entity.SubProduct;
import com.majestic.nails.mapper.SubProductMapper;

@Service
public class SubProductMapperImpl implements SubProductMapper {

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	@Override
	public SubProductDto toDto(SubProduct childService) {
		SubProductDto dto = new SubProductDto();
		if (childService != null) {
			dto.setContent(childService.getContent());
			//dto.setCreatedDate(childService.getCreatedDate().toString());
			dto.setId(childService.getId());
			dto.setImageKey(childService.getImage());
			dto.setImageBaseUrl("https://" + bucketName + ".s3.amazonaws.com/");
			dto.setIntro(childService.getIntro());
			dto.setName(childService.getName());
			dto.setParentServiceId(childService.getParentProduct().getId());
			dto.setPrice(childService.getPrice());
			//dto.setUpdatedDate(childService.getUpdatedDate().toString());
			dto.setUrl(childService.getUrl());
			dto.setParentServiceName(childService.getParentProduct().getName());
			dto.setIsShownHome(childService.isShownHome());
			dto.setPrice(childService.getPrice());
		} else {
			dto = null;
		}	
		return dto;
	}

}
