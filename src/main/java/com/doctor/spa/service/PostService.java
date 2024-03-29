package com.doctor.spa.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.dto.PostDto;
import com.doctor.spa.entity.Post;

public interface PostService {
	
	Page<PostDto> getPosts(Pageable pageable);
	
	Integer getPostsNo();
	
	Integer getPostsNo(Long id, String searchText);
	
	Post getSinglePost(String url);
	
	List<PostDto> getLatestPost();
	
	List<SubProductDto> getChildServices(String url);
	
	public Page<PostDto> getPostsWithConditions(Long id, String searchText, Pageable pageable);
	
	Map<String, Boolean> validateUrlNoId(String url);
	
	Map<String, Boolean> validateUrl(Long id, String url);
	
	PostDto createPost(PostDto dto, MultipartFile image);
	
	Boolean updatePost(PostDto dto, MultipartFile image);
	
	Boolean deletePost(Long id);
	
	PostDto getPost(long id);
	
	List<PostDto> getAll();
}
