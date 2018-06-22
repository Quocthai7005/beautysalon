package com.doctor.spa.service;

import java.util.List;

import com.doctor.spa.dto.ImageDto;
import com.doctor.spa.entity.PageText;

public interface PageTextService {

	PageText getTextByPageAndSection(String page, String section);
	
	List<PageText> findByPage(String page);
	
	List<ImageDto> getHeaderImage();
	
	Boolean updateImage(List<ImageDto> images);
	
	List<ImageDto> getShownImage();
}
