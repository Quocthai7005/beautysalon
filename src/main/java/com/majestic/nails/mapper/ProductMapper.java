package com.majestic.nails.mapper;

import com.majestic.nails.dto.ProductDto;
import com.majestic.nails.entity.Product;

public interface ProductMapper {

	ProductDto toDto(Product service);
	
	Product toEntity(ProductDto dto);
}
