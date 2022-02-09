package com.doctor.spa.mapper;

import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.entity.News;

public interface NewsMapper {

	NewsDto toDto(News news);
}
