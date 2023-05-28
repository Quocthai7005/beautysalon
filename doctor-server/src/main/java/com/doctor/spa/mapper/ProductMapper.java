package com.doctor.spa.mapper;

import com.doctor.spa.dto.ProductDto;
import com.doctor.spa.entity.Product;

public interface ProductMapper {

	ProductDto toDto(Product service);
	
	Product toEntity(ProductDto dto);
}
