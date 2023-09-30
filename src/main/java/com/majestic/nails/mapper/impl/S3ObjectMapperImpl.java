package com.majestic.nails.mapper.impl;

import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.majestic.nails.dto.S3ObjectDto;
import com.majestic.nails.mapper.S3ObjectMapper;

@Service
public class S3ObjectMapperImpl implements S3ObjectMapper {

	@Override
	public S3ObjectDto toDto(S3ObjectSummary s3Object) {
		S3ObjectDto dto = new S3ObjectDto();
		dto.setLastModified(s3Object.getLastModified());
		dto.setKey(s3Object.getKey());
		dto.setObjectClass(s3Object.getStorageClass());
		dto.setUri("https://" + s3Object.getBucketName() + ".s3.amazonaws.com/"+ s3Object.getKey());
		dto.setSize(s3Object.getSize());
		return dto;
	}

}
