package com.doctor.spa.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

	String uploadFile(final MultipartFile multipartFile);

}
