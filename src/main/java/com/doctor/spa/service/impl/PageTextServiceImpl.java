package com.doctor.spa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doctor.spa.dto.ImageDto;
import com.doctor.spa.entity.PageText;
import com.doctor.spa.repository.PageTextRepo;
import com.doctor.spa.service.PageTextService;

@Service
@Transactional
public class PageTextServiceImpl implements PageTextService{
	
	@Autowired
	private PageTextRepo pageTextRepo;

	
	@Override
	public PageText getTextByPageAndSection(String page, String section) {
		PageText pt = pageTextRepo.findByPageInAndSectionIn(page, section);
		return pt;
	}


	@Override
	public List<PageText> findByPage(String page) {
		List<PageText> pageTexts = pageTextRepo.findByPage(page);
		return pageTexts;
	}


	@Override
	public List<ImageDto> getHeaderImage() {
		List<PageText> images = pageTextRepo.findBySection("header_image");
		List<ImageDto> imageDtos = new ArrayList<ImageDto>();
		images.forEach(pageText -> {
			ImageDto imageDto = new ImageDto();
			imageDto.setId(pageText.getId());
			imageDto.setBase64Image(pageText.getContent());
			imageDto.setIsShownHome(pageText.getIsShownHome());
			imageDtos.add(imageDto);
		});
		return imageDtos;
	}


	@Override
	public Boolean updateImage(List<ImageDto> modifiedImages) {
		
		List<PageText> originImages = pageTextRepo.findBySection("header_image");
		for (int i = 0; i < originImages.size(); i++) {
			PageText image = pageTextRepo.findById(modifiedImages.get(i).getId());
			image.setContent(modifiedImages.get(i).getBase64Image());
			image.setIsShownHome(modifiedImages.get(i).getIsShownHome());
			pageTextRepo.save(image);
		}
		return true;
	}


	@Override
	public List<ImageDto> getShownImage() {
		List<PageText> pageTexts = pageTextRepo.findBySectionAndIsShownHomeTrue("header_image");
		List<ImageDto> imageDtos = new ArrayList<>();
		pageTexts.forEach(pageText -> {
			ImageDto dto = new ImageDto();
			dto.setBase64Image(pageText.getContent());
			dto.setId(pageText.getId());
			dto.setIsShownHome(pageText.getIsShownHome());
			imageDtos.add(dto);
		});
		return imageDtos;
	}
}
