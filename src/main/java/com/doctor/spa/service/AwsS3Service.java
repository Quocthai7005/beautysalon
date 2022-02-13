package com.doctor.spa.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3ObjectSummary;

public interface AwsS3Service {

	String uploadFile(final MultipartFile multipartFile);

	List<S3ObjectSummary> getAllItems();

	List<S3ObjectSummary> getNewsImage()

	List<S3ObjectSummary> getProductImage();
}
