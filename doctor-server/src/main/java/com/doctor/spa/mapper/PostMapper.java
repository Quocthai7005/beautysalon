package com.doctor.spa.mapper;

import com.doctor.spa.dto.PostDto;
import com.doctor.spa.entity.Post;

public interface PostMapper {

	PostDto toDto(Post post);
}
