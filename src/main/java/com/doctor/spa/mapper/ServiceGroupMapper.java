package com.doctor.spa.mapper;

import com.doctor.spa.dto.ServiceGroupDto;
import com.doctor.spa.entity.ServiceGroup;

public interface ServiceGroupMapper {

	ServiceGroupDto toDto(ServiceGroup service);
	
	ServiceGroup toEntity(ServiceGroupDto dto);
}
