package com.doctor.spa.mapper;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.doctor.spa.dto.S3ObjectDto;

public interface S3ObjectMapper {

	S3ObjectDto toDto(S3ObjectSummary s3Object);

}
