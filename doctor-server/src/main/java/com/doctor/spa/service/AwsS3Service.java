package com.doctor.spa.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.doctor.spa.dto.S3ObjectDto;

public interface AwsS3Service {

	String uploadFile(final MultipartFile multipartFile);

	List<S3ObjectSummary> getAllItems();

	Page<S3ObjectDto> getFiles(Pageable pageable, String Directory);

	int getFilesNo(String Directory);
	
	boolean deleteFile(String key);
}
