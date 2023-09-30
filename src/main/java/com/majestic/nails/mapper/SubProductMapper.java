package com.majestic.nails.mapper;

import com.majestic.nails.dto.SubProductDto;
import com.majestic.nails.entity.SubProduct;

public interface SubProductMapper {
	
	SubProductDto toDto(SubProduct childService);

}
