package com.majestic.nails.mapper;

import com.majestic.nails.dto.PostDto;
import com.majestic.nails.entity.Post;

public interface PostMapper {

	PostDto toDto(Post post);
}
