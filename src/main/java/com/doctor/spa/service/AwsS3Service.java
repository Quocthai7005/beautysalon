package com.doctor.spa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public interface AwsS3Service {

	String uploadFile(final MultipartFile multipartFile);

	List<S3ObjectSummary> getAllItems();

	Page<S3ObjectSummary> getFiles(Pageable pageable, String Directory);

	int getFilesNo(String Directory);

	S3Object getFile(String key);
}
