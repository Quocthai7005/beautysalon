package com.doctor.spa.mapper.impl;

import org.springframework.stereotype.Service;

import com.doctor.spa.dto.ChildServiceDto;
import com.doctor.spa.entity.ChildService;
import com.doctor.spa.mapper.ChildServiceMapper;

@Service
public class ChildServiceMapperImpl implements ChildServiceMapper{

	@Override
	public ChildServiceDto toDto(ChildService childService) {
		ChildServiceDto dto = new ChildServiceDto();
		if (childService != null) {
			dto.setContent(childService.getContent());
			dto.setCreatedDate(childService.getCreatedDate().toString());
			dto.setId(childService.getId());
			dto.setImage(childService.getImage());
			dto.setIntro(childService.getIntro());
			dto.setName(childService.getName());
			dto.setParentServiceId(childService.getParentService().getId());
			dto.setUpdatedDate(childService.getUpdatedDate().toString());
			dto.setUrl(childService.getUrl());
			dto.setParentServiceName(childService.getParentService().getName());
			dto.setIsShownHome(childService.isShownHome());
		} else {
			dto = null;
		}	
		return dto;
	}

}
