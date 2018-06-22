package com.doctor.spa.mapper;

import com.doctor.spa.dto.ChildServiceDto;
import com.doctor.spa.entity.ChildService;

public interface ChildServiceMapper {
	
	ChildServiceDto toDto(ChildService childService);

}
