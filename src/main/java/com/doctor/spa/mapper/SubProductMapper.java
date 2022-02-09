package com.doctor.spa.mapper;

import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.entity.SubProduct;

public interface SubProductMapper {
	
	SubProductDto toDto(SubProduct childService);

}
