package com.doctor.spa.service;

import java.util.List;
import java.util.Map;

import com.doctor.spa.dto.ImageDto;
import com.doctor.spa.entity.PageText;

public interface PageTextService {

	PageText getTextByPageAndSection(String page, String section);
	
	List<PageText> findByPage(String page);
	
	List<ImageDto> getHeaderImage();
	
	Boolean updateImage(List<ImageDto> images);
	
	List<ImageDto> getShownImage();
	
	Map<String, String> getContact();
	
	Boolean updateContact(Map<String, String> contact);
}
