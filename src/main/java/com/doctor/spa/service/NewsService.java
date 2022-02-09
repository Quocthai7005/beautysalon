package com.doctor.spa.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.doctor.spa.dto.SubProductDto;
import com.doctor.spa.dto.NewsDto;
import com.doctor.spa.entity.News;

public interface NewsService {
	
	Page<NewsDto> getPosts(Pageable pageable);
	
	Integer getPostsNo();
	
	Integer getPostsNo(Long id, String searchText);
	
	News getSinglePost(String url);
	
	List<NewsDto> getLatestPost();
	
	List<SubProductDto> getChildServices(String url);
	
	public Page<NewsDto> getPostsWithConditions(Long id, String searchText, Pageable pageable);
	
	Map<String, Boolean> validateUrlNoId(String url);
	
	Map<String, Boolean> validateUrl(Long id, String url);
	
	Boolean createPost(NewsDto dto, MultipartFile image);
	
	Boolean updatePost(NewsDto dto, MultipartFile image);
	
	Boolean deletePost(Long id);
	
	NewsDto getPost(long id);
}
