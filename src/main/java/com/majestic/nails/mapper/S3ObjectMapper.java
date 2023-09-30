package com.majestic.nails.mapper;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.majestic.nails.dto.S3ObjectDto;

public interface S3ObjectMapper {

	S3ObjectDto toDto(S3ObjectSummary s3Object);

}
