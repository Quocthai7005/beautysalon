package com.doctor.spa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3ObjectSummary;

public interface AwsS3Service {

	String uploadFile(final MultipartFile multipartFile);

	List<S3ObjectSummary> getAllItems();

	Page<S3ObjectSummary> getNewsImages(Pageable pageable, String lastKey);

	Page<S3ObjectSummary> getProductImages(Pageable pageable, String lastKey);

	int getNewsImagesNo();

	int getProductImagesNo();
}
