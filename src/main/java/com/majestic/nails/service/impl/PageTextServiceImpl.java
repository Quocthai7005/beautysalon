package com.majestic.nails.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.majestic.nails.entity.PageText;
import com.majestic.nails.repository.PageTextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.majestic.nails.dto.ImageDto;
import com.majestic.nails.service.PageTextService;

@Service
@Transactional
public class PageTextServiceImpl implements PageTextService{
	
	@Autowired
	private PageTextRepository pageTextRepository;

	
	@Override
	public PageText getTextByPageAndSection(String page, String section) {
		PageText pt = pageTextRepository.findByPageInAndSectionIn(page, section);
		return pt;
	}


	@Override
	public List<PageText> findByPage(String page) {
		List<PageText> pageTexts = pageTextRepository.findByPage(page);
		return pageTexts;
	}


	@Override
	public List<ImageDto> getHeaderImage() {
		List<PageText> images = pageTextRepository.findBySection("header_image");
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
		
		List<PageText> originImages = pageTextRepository.findBySection("header_image");
		for (int i = 0; i < originImages.size(); i++) {
			PageText image = pageTextRepository.findById(modifiedImages.get(i).getId());
			image.setContent(modifiedImages.get(i).getBase64Image());
			image.setIsShownHome(modifiedImages.get(i).getIsShownHome());
			pageTextRepository.save(image);
		}
		return true;
	}


	@Override
	public List<ImageDto> getShownImage() {
		List<PageText> pageTexts = pageTextRepository.findBySectionAndIsShownHomeTrue("header_image");
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


	@Override
	public Map<String, String> getContact() {
		Map<String, String> result = new HashMap<String, String>();
		List<PageText> contact = pageTextRepository.findBySection("footer");
		contact.forEach(s -> {
			result.put(s.getTitle(), s.getContent());
		});
		return result;
	}


	@Override
	public Boolean updateContact(Map<String, String> contact) {

		PageText address = pageTextRepository.findByTitle("address");
		PageText phone = pageTextRepository.findByTitle("phone");
		PageText map = pageTextRepository.findByTitle("map");
		PageText facebook = pageTextRepository.findByTitle("facebook");
		
		address.setContent(contact.get("address"));
		phone.setContent(contact.get("phone"));
		map.setContent(contact.get("map"));
		facebook.setContent(contact.get("facebook"));
		
		pageTextRepository.save(address);
		pageTextRepository.save(phone);
		pageTextRepository.save(map);
		pageTextRepository.save(facebook);
		
		return true;
	}
}
